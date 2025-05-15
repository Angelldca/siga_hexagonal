package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteListUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.menu.CreateMenuCommand;
import com.angelldca.siga.application.port.in.command.menu.UpdateMenuCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.*;
import com.angelldca.siga.application.port.out.Menu.MenuCRUDPort;
import com.angelldca.siga.application.port.out.evento.GetAllEventById;
import com.angelldca.siga.application.port.out.menuEvento.GetMenuEventoByMenuIdPort;
import com.angelldca.siga.application.port.out.menuEvento.MenuEventoCRUDPort;
import com.angelldca.siga.application.port.out.plato.LoadPlatosPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.MenuResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.*;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

@UseCase
public class MenuService implements
        CreateUseCase<MenuEvento, CreateMenuCommand>,
        UpdateUseCase<Menu, UpdateMenuCommand,Long>,
        DeleteUseCase<Menu,Long>, DeleteListUseCase<Long>,
        GetUseCase<Long>,
        ListUseCase {

    private final MenuCRUDPort menuCRUDPort;
    private final MenuEventoCRUDPort menuEventoCRUDPort;
    private final GetPort<Evento,Long> eventoGetPort;
    private final LoadPlatosPort loadPlatosPort;
    private final DeleteListPort<Long> deleteListPort;
    private final GetMenuEventoByMenuIdPort getMenuEventoByMenuIdPort;

    public MenuService(
            @Qualifier("menuPersistenceAdapter")MenuCRUDPort menuCRUDPort,
            MenuEventoCRUDPort menuEventoCRUDPort,
            @Qualifier("eventPersistenceAdapter") GetPort<Evento,Long> eventoGetPort,
            LoadPlatosPort loadPlatosPort,
            @Qualifier("menuPersistenceAdapter")DeleteListPort<Long> deleteListPort,
            @Qualifier("menuEventoPersistenceAdapter") GetMenuEventoByMenuIdPort getMenuEventoByMenuIdPort) {
        this.menuCRUDPort = menuCRUDPort;
        this.menuEventoCRUDPort = menuEventoCRUDPort;
        this.eventoGetPort = eventoGetPort;
        this.loadPlatosPort = loadPlatosPort;
        this.deleteListPort = deleteListPort;
        this.getMenuEventoByMenuIdPort = getMenuEventoByMenuIdPort;
    }



    @Override
    public MenuEvento create(CreateMenuCommand command) {
        List<Plato> platos = this.loadPlatosPort.loadAllByIds(command.getPlatosId());
        Double total = platos.stream()
                .mapToDouble(Plato::getPrecio)
                .sum();

        Menu entity = this.menuCRUDPort.save(new Menu(
                null, total,
                command.getDisponible(),false,
                platos
        ));
        Evento evento = eventoGetPort.obtenerPorId(command.getEventoId());

        return  this.menuEventoCRUDPort.save(new MenuEvento(
                UUID.randomUUID(),
                entity,evento,command.getFecha(),false
        ));
    }

    @Override
    public Menu delete(Long id) {
        Menu menu = menuCRUDPort.obtenerPorId(id);
        menu.setDisponible(false);
        return this.menuCRUDPort.save(menu);
    }

    @Override
    public Menu update(UpdateMenuCommand command, Long id) {
        List<Plato> platos = this.loadPlatosPort.loadAllByIds(command.getPlatosId());
        Menu entity =  this.menuCRUDPort.obtenerPorId(id);
        entity.setPlatos(platos);
        double total = platos.stream()
                .mapToDouble(Plato::getPrecio)
                .sum();
        entity.setTotalPrecio(total);
        entity.setDisponible(command.getDisponible());
        MenuEvento menuEvento = this.getMenuEventoByMenuIdPort.getMenuEventoByMenuId(id);
        menuEvento.setEvento(this.eventoGetPort.obtenerPorId(command.getEventoId()));
        menuEvento.setFecha(command.getFecha());
        this.menuEventoCRUDPort.save(menuEvento);
        return this.menuCRUDPort.save(entity);
    }

    @Override
    public IResponse getById(Long id) {
        Menu entity =  this.menuCRUDPort.obtenerPorId(id);
        return new MenuResponse(entity);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<MenuEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<MenuEntity> data = this.menuCRUDPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<MenuEntity> data) {
        List<MenuResponse> menuResponse = new ArrayList<>();
        for (MenuEntity p : data.getContent()) {
            menuResponse.add(new MenuResponse(MenuMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(menuResponse, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void deleteList(DeleteListCommand<Long> command) {
        this.deleteListPort.deleteList(command.getIds());
    }
}
