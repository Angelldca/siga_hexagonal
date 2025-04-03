package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.menu.CreateMenuCommand;
import com.angelldca.siga.application.port.in.command.menu.UpdateMenuCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.Menu.MenuCRUDPort;
import com.angelldca.siga.application.port.out.plato.LoadPlatosPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.MenuResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import java.util.List;

@UseCase
public class MenuService implements
        CreateUseCase<Menu, CreateMenuCommand>,
        UpdateUseCase<Menu, UpdateMenuCommand,Long>,
        DeleteUseCase<Menu,Long>,
        GetUseCase<Long>,
        ListUseCase {

    private final MenuCRUDPort menuCRUDPort;
    private final GetPort<Evento,Long> eventoGetPort;
    private final LoadPlatosPort loadPlatosPort;


    public MenuService(
            @Qualifier("menuPersistenceAdapter")MenuCRUDPort menuCRUDPort,
            @Qualifier("eventPersistenceAdapter") GetPort<Evento,Long> eventoGetPort, LoadPlatosPort loadPlatosPort) {
        this.menuCRUDPort = menuCRUDPort;
        this.eventoGetPort = eventoGetPort;
        this.loadPlatosPort = loadPlatosPort;

    }

    @Override
    public Menu create(CreateMenuCommand command) {
        List<Plato> platos = this.loadPlatosPort.loadAllByIds(command.getPlatosId());
        double total = platos.stream()
                .mapToDouble(Plato::getPrecio)
                .sum();

        Menu entity = new Menu(
                null, total,
                command.getDisponible(),
                eventoGetPort.obtenerPorId(command.getEventoId()),
                platos
        );

        return this.menuCRUDPort.save(entity);
    }

    @Override
    public Menu delete(Long id) {
        return this.menuCRUDPort.delete(id);
    }

    @Override
    public Menu update(UpdateMenuCommand command, Long id) {
        List<Plato> platos = this.loadPlatosPort.loadAllByIds(command.getPlatosId());
        Menu entity =  this.menuCRUDPort.obtenerPorId(id);
        entity.setEvento(eventoGetPort.obtenerPorId(command.getEventoId()));
        entity.setPlatos(platos);
        double total = platos.stream()
                .mapToDouble(Plato::getPrecio)
                .sum();
        entity.setTotalPrecio(total);
        entity.setDisponible(command.getDisponible());
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
}
