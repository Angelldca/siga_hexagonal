package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.module.CreateModuleCommand;
import com.angelldca.siga.application.port.in.command.module.UpdateModuleCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.module.ModuleCRUDPort;
import com.angelldca.siga.application.port.out.permission.LoadPermissionPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.*;
import com.angelldca.siga.domain.model.Module;
import com.angelldca.siga.domain.model.Permission;
import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UserEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@UseCase
public class ModuleService implements
        CreateUseCase<Module, CreateModuleCommand>,
        UpdateUseCase<Module, UpdateModuleCommand, Long>,
        DeleteUseCase<Module,Long>,
        GetUseCase<Long>,
        ListUseCase {
    private final ModuleCRUDPort crudPort;

    private final LoadPermissionPort loadPermissionPort;

    public ModuleService(@Qualifier("modulePersistenceAdapter") ModuleCRUDPort crudPort, LoadPermissionPort loadPermissionPort) {
        this.crudPort = crudPort;

        this.loadPermissionPort = loadPermissionPort;
    }

    @Override
    public Module create(CreateModuleCommand command) {
        Module domain = new Module(
                null,
                command.getName(),
                command.getImage(),
                command.getDescription(),
                Collections.emptyList());
        return this.crudPort.save(domain);
    }

    @Override
    public Module delete(Long id) {
        return this.crudPort.delete(id);
    }

    @Override
    public Module update(UpdateModuleCommand command, Long id) {
        Module domain = this.crudPort.obtenerPorId(id);
        domain.setDescription(command.getDescription());
        domain.setName(command.getName());
        domain.setImage(command.getImage());
        return this.crudPort.save(domain);
    }

    @Override
    public IResponse getById(Long id) {
        Module domain = this.crudPort.obtenerPorId(id);
        return new ModuleResponse(domain);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<ModuleEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<ModuleEntity> data = this.crudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<ModuleEntity> data) {
        List<ModuleResponse> response = new ArrayList<>();
        for (ModuleEntity p : data.getContent()) {
            response.add(new ModuleResponse(ModuleMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
