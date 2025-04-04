package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;

import com.angelldca.siga.application.port.in.command.menuEvento.CreateMenuEventoCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.menuEvento.MenuEventoCRUDPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.MenuEventoResponse;
import com.angelldca.siga.common.response.MenuResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.MenuEvento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menuEvento.MenuEventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menuEvento.MenuEventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class MenuEventoService implements
        UpdateUseCase<MenuEvento, CreateMenuEventoCommand,UUID>,
        DeleteUseCase<MenuEvento,UUID>,
        GetUseCase<UUID>,
        ListUseCase{
    private final MenuEventoCRUDPort menuEventoCRUDPort;
    private final GetPort<Evento,Long> eventoGetPort;
    private final GetPort<Menu,Long> menuGetPort;

    public MenuEventoService(
            @Qualifier("menuEventoPersistenceAdapter")MenuEventoCRUDPort menuEventoCRUDPort, GetPort<Evento, Long> eventoGetPort, GetPort<Menu, Long> menuGetPort) {
        this.menuEventoCRUDPort = menuEventoCRUDPort;
        this.eventoGetPort = eventoGetPort;
        this.menuGetPort = menuGetPort;
    }

    @Override
    public MenuEvento delete(UUID id) {
        return this.menuEventoCRUDPort.delete(id);
    }

    @Override
    public MenuEvento update(CreateMenuEventoCommand command, UUID id) {
        MenuEvento menuEvento = this.menuEventoCRUDPort.obtenerPorId(id);
        menuEvento.setEvento(this.eventoGetPort.obtenerPorId(command.getEventoId()));
        menuEvento.setMenu(this.menuGetPort.obtenerPorId(command.getMenuId()));
        return this.menuEventoCRUDPort.save(menuEvento);
    }

    @Override
    public IResponse getById(UUID id) {
       MenuEvento entity =  this.menuEventoCRUDPort.obtenerPorId(id);
        return new MenuEventoResponse(entity);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<MenuEventoEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<MenuEventoEntity> data = this.menuEventoCRUDPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<MenuEventoEntity> data) {
        List<MenuEventoResponse> menuResponse = new ArrayList<>();
        for (MenuEventoEntity p : data.getContent()) {
            menuResponse.add(new MenuEventoResponse(MenuEventoMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(menuResponse, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
