package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.business.CreateBusinessCommand;
import com.angelldca.siga.application.port.in.command.business.UpdateBusinessCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.business.BusinessCRUDPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.*;
import com.angelldca.siga.domain.model.Empresa;

import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class BusinessService implements
        CreateUseCase<Empresa, CreateBusinessCommand>,
        UpdateUseCase<Empresa, UpdateBusinessCommand, UUID>,
        DeleteUseCase<Empresa,UUID>,
        GetUseCase<UUID>,
        ListUseCase {

    private final BusinessCRUDPort crudPort;
    private final EmpresaMapper mapper;

    public BusinessService(@Qualifier("empresaPersistenceAdapter")BusinessCRUDPort crudPort, EmpresaMapper mapper) {
        this.crudPort = crudPort;
        this.mapper = mapper;
    }

    @Override
    public Empresa create(CreateBusinessCommand command) {
        Empresa domain = new Empresa(
                UUID.randomUUID(),
                command.getNombre(),
                command.getLogo(),
                command.getAddress());
        return this.crudPort.save(domain);
    }

    @Override
    public Empresa delete(UUID id) {
        return this.crudPort.delete(id);
    }

    @Override
    public Empresa update(UpdateBusinessCommand command, UUID id) {
        Empresa domain = this.crudPort.obtenerPorId(id);
        domain.setAddress(command.getAddress());
        domain.setLogo(command.getLogo());
        domain.setNombre(command.getNombre());
        return this.crudPort.save(domain);
    }

    @Override
    public IResponse getById(UUID id) {
        Empresa domain = this.crudPort.obtenerPorId(id);
        return new BusinessResponse(domain);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<EmpresaEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<EmpresaEntity> data = this.crudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<EmpresaEntity> data) {
        List<BusinessResponse> response = new ArrayList<>();
        for (EmpresaEntity p : data.getContent()) {
            response.add(new BusinessResponse(mapper.entityToDomain(p)));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
