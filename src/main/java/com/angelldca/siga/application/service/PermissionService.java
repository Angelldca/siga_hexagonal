package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.permission.CreatePermissionCommand;
import com.angelldca.siga.application.port.in.command.permission.UpdatePermissionCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.permission.LoadPermissionPort;
import com.angelldca.siga.application.port.out.permission.PermissionCRUDPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.*;
import com.angelldca.siga.domain.model.Module;
import com.angelldca.siga.domain.model.Permission;

import com.angelldca.siga.infrastructure.adapter.out.persistence.permission.PermissionEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.permission.PermissionMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@UseCase
public class PermissionService implements
        CreateUseCase<Permission, CreatePermissionCommand>,
        UpdateUseCase<Permission, UpdatePermissionCommand, Long>,
        DeleteUseCase<Permission,Long>,
        GetUseCase<Long>,
        ListUseCase {

    private final PermissionCRUDPort crudPort;
    private final LoadPermissionPort loadPermissionPort;
    private final GetPort<Module,Long> getPortModule;


    public PermissionService(@Qualifier("permissionPersistenceAdapter") PermissionCRUDPort crudPort, LoadPermissionPort loadPermissionPort, GetPort<Module, Long> getPortModule) {
        this.crudPort = crudPort;
        this.loadPermissionPort = loadPermissionPort;
        this.getPortModule = getPortModule;

    }

    @Override
    public Permission create(CreatePermissionCommand command) {
       Module module = this.getPortModule.obtenerPorId(command.getModuleId());
        Permission domain = new Permission(
               null,
                module.getName()+":"+command.getAction(),
                command.getDescription(),
                command.getAction(),
                module

        );
        return this.crudPort.save(domain);
    }

    @Override
    public Permission delete(Long id) {
        return this.crudPort.delete(id);
    }

    @Override
    public Permission update(UpdatePermissionCommand command, Long id) {
        Permission domain = this.crudPort.obtenerPorId(id);
        Module module = this.getPortModule.obtenerPorId(command.getModuleId());

        domain.setCode(module.getName()+":"+command.getAction());
        domain.setDescription(command.getDescription());
        domain.setAction(command.getAction());
        domain.setModule(module);
        return this.crudPort.save(domain);
    }

    @Override
    public IResponse getById(Long id) {
        Permission domain = this.crudPort.obtenerPorId(id);
        return new PermissionResponse(domain);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<PermissionEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<PermissionEntity> data = this.crudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<PermissionEntity> data) {
        List<PermissionResponse> response = new ArrayList<>();
        for (PermissionEntity p : data.getContent()) {
            response.add(new PermissionResponse(PermissionMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
