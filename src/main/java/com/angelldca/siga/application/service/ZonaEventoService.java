package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.zonaEvento.BulkCreateZonaEventoUseCase;
import com.angelldca.siga.application.port.in.command.zonaEvento.BulkUpdateZonaEventoUseCase;
import com.angelldca.siga.application.port.in.command.zonaEvento.CreateZonaEventoCommand;
import com.angelldca.siga.application.port.in.command.zonaEvento.UpdateZonaEventoCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.application.port.out.evento.GetAllEventById;
import com.angelldca.siga.application.port.out.zonaEvento.DeletePortByZonaId;
import com.angelldca.siga.application.port.out.zonaEvento.SaveAllZonaEvento;
import com.angelldca.siga.application.port.out.zonaEvento.ZonaEventoCrudPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.response.ZonaEventoResponse;

import com.angelldca.siga.domain.model.Empresa;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class ZonaEventoService implements
        BulkCreateZonaEventoUseCase,
        BulkUpdateZonaEventoUseCase,
        DeleteUseCase<ZonaEvento, UUID>,
        GetUseCase<UUID>,
        ListUseCase {
    private final ZonaEventoCrudPort zonaEventoCrudPort;
    private final GetPort<Zona,Long> getPortZona;
    private final GetPort<Empresa,UUID> getPortBusiness;
    private final SavePort<Zona> savePortZona;
    private final GetAllEventById getAllEventById;
    private final SaveAllZonaEvento saveAllZonaEvento;
    private final DeletePortByZonaId deletePortByZonaId;

    public ZonaEventoService(
            @Qualifier("zonaEventoPersistenceAdapter") ZonaEventoCrudPort zonaEventoCrudPort,
            @Qualifier("zonaPersistenceAdapter") GetPort<Zona, Long> getPortZona,
            @Qualifier("empresaPersistenceAdapter") GetPort<Empresa, UUID> getPortBusiness, @Qualifier("zonaPersistenceAdapter") SavePort<Zona> savePortZona,
            GetAllEventById getAllEventById, SaveAllZonaEvento saveAllZonaEvento,
            DeletePortByZonaId deletePortByZonaId) {
        this.zonaEventoCrudPort = zonaEventoCrudPort;
        this.getPortZona = getPortZona;
        this.getPortBusiness = getPortBusiness;
        this.savePortZona = savePortZona;
        this.getAllEventById = getAllEventById;
        this.saveAllZonaEvento = saveAllZonaEvento;
        this.deletePortByZonaId = deletePortByZonaId;
    }

    @Override
    @Transactional
    public List<ZonaEvento> create(CreateZonaEventoCommand command) {
        Zona createZona = new Zona(
                null,
                command.getNombre(),
                getPortBusiness.obtenerPorId(command.getEmpresa()),
                false);
        Zona zona = savePortZona.save(createZona);
        List<Evento> eventos = getAllEventById.getAllById(command.getEventosId());
        List<ZonaEvento> relaciones = eventos.stream()
                .map(ev -> new ZonaEvento(UUID.randomUUID(), zona, ev))
                .toList();
        return saveAllZonaEvento.saveAllZonaEvento(relaciones);
    }

    @Override
    public ZonaEvento delete(UUID id) {
        return this.zonaEventoCrudPort.delete(id);
    }

    @Override
    @Transactional
    public List<ZonaEvento> update(UpdateZonaEventoCommand command, Long id) {
        Zona zona = this.getPortZona.obtenerPorId(id);
        zona.setNombre(command.getNombre());
        this.savePortZona.save(zona);
        deletePortByZonaId.deleteByZonaId(zona.getId());
        List<Evento> eventos = getAllEventById.getAllById(command.getEventosId());
        List<ZonaEvento> nuevos = eventos.stream()
                .map(evento -> new ZonaEvento(UUID.randomUUID(), zona, evento))
                .toList();

       return this.saveAllZonaEvento.saveAllZonaEvento(nuevos);
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
