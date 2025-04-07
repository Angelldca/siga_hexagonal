package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.zonaEvento.CreateZonaEventoCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.zonaEvento.ZonaEventoCrudPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.response.ZonaEventoResponse;

import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.domain.model.ZonaEvento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zonaEvento.ZonaEventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zonaEvento.ZonaEventoMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class ZonaEventoService implements
        CreateUseCase<ZonaEvento,CreateZonaEventoCommand>,
        UpdateUseCase<ZonaEvento, CreateZonaEventoCommand,UUID>,
        DeleteUseCase<ZonaEvento, UUID>,
        GetUseCase<UUID>,
        ListUseCase {
    private final ZonaEventoCrudPort zonaEventoCrudPort;
    private final GetPort<Evento,Long> getPortEvento;
    private final GetPort<Zona,Long> getPortZona;

    public ZonaEventoService(
            @Qualifier("zonaEventoPersistenceAdapter") ZonaEventoCrudPort zonaEventoCrudPort,
            @Qualifier("eventPersistenceAdapter") GetPort<Evento, Long> getPortEvento,
            @Qualifier("zonaPersistenceAdapter") GetPort<Zona, Long> getPortZona) {
        this.zonaEventoCrudPort = zonaEventoCrudPort;
        this.getPortEvento = getPortEvento;
        this.getPortZona = getPortZona;
    }

    @Override
    public ZonaEvento create(CreateZonaEventoCommand command) {
        Zona zona = this.getPortZona.obtenerPorId(command.getZonaId());
        Evento evento = this.getPortEvento.obtenerPorId(command.getEventoId());

        ZonaEvento zonaEvento = new ZonaEvento(
                UUID.randomUUID(),
                zona,evento
        );
        return this.zonaEventoCrudPort.save(zonaEvento);
    }

    @Override
    public ZonaEvento delete(UUID id) {
        return this.zonaEventoCrudPort.delete(id);
    }

    @Override
    public ZonaEvento update(CreateZonaEventoCommand command, UUID id) {
        ZonaEvento zonaEvento = this.zonaEventoCrudPort.obtenerPorId(id);
        Zona zona = this.getPortZona.obtenerPorId(command.getZonaId());
        Evento evento = this.getPortEvento.obtenerPorId(command.getEventoId());
        zonaEvento.setEvento(evento);
        zonaEvento.setZona(zona);
        return this.zonaEventoCrudPort.save(zonaEvento);
    }

    @Override
    public IResponse getById(UUID id) {
        return new ZonaEventoResponse(this.zonaEventoCrudPort.obtenerPorId(id));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<MenuEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<ZonaEventoEntity> data = this.zonaEventoCrudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<ZonaEventoEntity> data) {
        List<ZonaEventoResponse> responses = new ArrayList<>();
        for (ZonaEventoEntity p : data.getContent()) {
            responses.add(new ZonaEventoResponse(ZonaEventoMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(responses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
