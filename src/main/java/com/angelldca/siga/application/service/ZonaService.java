package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteListUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.zona.CreateZonaCommand;
import com.angelldca.siga.application.port.in.command.zona.UpdateZonaCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.DeleteListCommand;
import com.angelldca.siga.application.port.out.DeleteListPort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.zona.ZonaCrudPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.response.ZonaResponse;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;

import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zona.ZonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zona.ZonaMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class ZonaService implements
        CreateUseCase<Zona, CreateZonaCommand>,
        UpdateUseCase<Zona, UpdateZonaCommand,Long>,
        DeleteUseCase<Zona,Long>,
        GetUseCase<Long>,
        DeleteListUseCase<Long>,
        ListUseCase {

    private final ZonaCrudPort zonaCrudPort;
    private final GetPort<Empresa, UUID> getPortEmpresa;
    private final DeleteListPort<Long> deleteListPort;

    public ZonaService(
            @Qualifier("zonaPersistenceAdapter")ZonaCrudPort zonaCrudPort,
            @Qualifier("empresaPersistenceAdapter")GetPort<Empresa, UUID> getPortEmpresa,
            @Qualifier("zonaPersistenceAdapter")DeleteListPort<Long> deleteListPort) {
        this.zonaCrudPort = zonaCrudPort;
        this.getPortEmpresa = getPortEmpresa;
        this.deleteListPort = deleteListPort;
    }

    @Override
    public Zona create(CreateZonaCommand command) {
        Empresa empresa = this.getPortEmpresa.obtenerPorId(command.getEmpresaId());
        Zona zona = new Zona(
                null, command.getNombre(),
                empresa,false
        );
        return this.zonaCrudPort.save(zona);
    }

    @Override
    public Zona delete(Long id) {
        return this.zonaCrudPort.delete(id);
    }

    @Override
    public Zona update(UpdateZonaCommand command, Long id) {
        Zona zona = this.zonaCrudPort.obtenerPorId(id);
        zona.setNombre(command.getNombre());

        return this.zonaCrudPort.save(zona);
    }

    @Override
    public IResponse getById(Long id) {
        return new ZonaResponse(this.zonaCrudPort.obtenerPorId(id));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<MenuEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<ZonaEntity> data = this.zonaCrudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<ZonaEntity> data) {
        List<ZonaResponse> responses = new ArrayList<>();
        for (ZonaEntity p : data.getContent()) {
            responses.add(new ZonaResponse(ZonaMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(responses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void deleteList(DeleteListCommand<Long> command) {
        this.deleteListPort.deleteList(command.getIds());
    }
}
