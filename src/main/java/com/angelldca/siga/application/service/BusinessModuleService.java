package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.businessModule.CreateBusinessModuleCommand;
import com.angelldca.siga.application.port.in.command.businessModule.UpdateBusinessModuleCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.businessModule.BusinessModuleCRUDPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.*;
import com.angelldca.siga.domain.model.BusinessModule;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Module;

import com.angelldca.siga.infrastructure.adapter.out.persistence.business_module.BusinessModuleEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.business_module.BusinessModuleMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class BusinessModuleService implements
        CreateUseCase<BusinessModule, CreateBusinessModuleCommand>,
        UpdateUseCase<BusinessModule, UpdateBusinessModuleCommand, UUID>,
        DeleteUseCase<BusinessModule,UUID>,
        GetUseCase<UUID>,
        ListUseCase {

    private final BusinessModuleCRUDPort crudPort;
    private final GetPort<Empresa,UUID> getPortBusiness;
    private final GetPort<Module,Long> getPortModule;


    public BusinessModuleService(BusinessModuleCRUDPort crudPort, GetPort<Empresa, UUID> getPortBusiness, GetPort<Module, Long> getPortModule) {
        this.crudPort = crudPort;
        this.getPortBusiness = getPortBusiness;
        this.getPortModule = getPortModule;

    }


    @Override
    public BusinessModule create(CreateBusinessModuleCommand command) {
        BusinessModule domain = new BusinessModule(
                UUID.randomUUID(),
                this.getPortBusiness.obtenerPorId(command.getEmpresaId()),
                this.getPortModule.obtenerPorId(command.getModulesId())

        );
        return this.crudPort.save(domain);
    }

    @Override
    public BusinessModule delete(UUID id) {
        return this.crudPort.delete(id);
    }

    @Override
    public BusinessModule update(UpdateBusinessModuleCommand command, UUID id) {
        BusinessModule domain = this.crudPort.obtenerPorId(id);

        domain.setBusiness( this.getPortBusiness.obtenerPorId(command.getEmpresaId()));
        domain.setModule(this.getPortModule.obtenerPorId(command.getModulesId()));

        return this.crudPort.save(domain);
    }

    @Override
    public IResponse getById(UUID id) {
        BusinessModule domain = this.crudPort.obtenerPorId(id);
        return new BusinessModuleResponse(domain);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<BusinessModuleEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<BusinessModuleEntity> data = this.crudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<BusinessModuleEntity> data) {
        List<BusinessModuleResponse> response = new ArrayList<>();
        for (BusinessModuleEntity p : data.getContent()) {
            response.add(new BusinessModuleResponse(BusinessModuleMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}