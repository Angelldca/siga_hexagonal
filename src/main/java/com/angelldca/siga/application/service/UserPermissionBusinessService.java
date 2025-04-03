package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.userPermissionBusiness.CreateUserPermissionBusinessCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.permission.LoadPermissionPort;
import com.angelldca.siga.application.port.out.user_permission_business.UserPermissionBusinessCRUDPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.*;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Permission;
import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UsuarioMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
public class UserPermissionBusinessService implements
        CreateUseCase<UserPermissionBusiness, CreateUserPermissionBusinessCommand>,
        UpdateUseCase<UserPermissionBusiness, CreateUserPermissionBusinessCommand, UUID>,
        DeleteUseCase<UserPermissionBusiness,UUID>,
        GetUseCase<UUID>,
        ListUseCase {

    private final UserPermissionBusinessCRUDPort crudPort;
    private final LoadPermissionPort loadPermissionPort;
    private final GetPort<Permission,Long> getPortPermission;
    private final GetPort<User,UUID> getPortUser;
    private final GetPort<Empresa,UUID> getPortBusiness;


    public UserPermissionBusinessService(@Qualifier("UserPermissionBusinessPersistenceAdapter") UserPermissionBusinessCRUDPort crudPort,
                                         LoadPermissionPort loadPermissionPort, GetPort<Permission, Long> getPortPermission,
                                         GetPort<User, UUID> getPortUser, GetPort<Empresa, UUID> getPortBusiness) {
        this.crudPort = crudPort;
        this.loadPermissionPort = loadPermissionPort;
        this.getPortPermission = getPortPermission;
        this.getPortUser = getPortUser;
        this.getPortBusiness = getPortBusiness;


    }

    @Override
    public UserPermissionBusiness create(CreateUserPermissionBusinessCommand command) {
        Permission permissions = this.getPortPermission.obtenerPorId(command.getPermissionId());
        User user = this.getPortUser.obtenerPorId(command.getUserId());
        Empresa business = this.getPortBusiness.obtenerPorId(command.getBusinessId());

        UserPermissionBusiness domain = new UserPermissionBusiness(
                UUID.randomUUID(),user,permissions,business
        );
        return this.crudPort.save(domain);
    }

    @Override
    public UserPermissionBusiness delete(UUID id) {
         return this.crudPort.delete(id);
    }

    @Override
    public UserPermissionBusiness update(CreateUserPermissionBusinessCommand command, UUID id) {


        Permission permissions = this.getPortPermission.obtenerPorId(command.getPermissionId());
        User user = this.getPortUser.obtenerPorId(command.getUserId());
        Empresa business = this.getPortBusiness.obtenerPorId(command.getBusinessId());
        UserPermissionBusiness domain = this.crudPort.obtenerPorId(id);
        domain.setPermission(permissions);
        domain.setUser(user);
        domain.setEmpresa(business);
        return this.crudPort.save(domain);
    }

    @Override
    public IResponse getById(UUID id) {
        UserPermissionBusiness domain = this.crudPort.obtenerPorId(id);
        return new UserPermissionBusinessResponse(domain);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<UserPermissionBusinessEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<UserPermissionBusinessEntity> data = this.crudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<UserPermissionBusinessEntity> data) {
        List<UserPermissionBusinessResponse> response = new ArrayList<>();
        for (UserPermissionBusinessEntity p : data.getContent()) {
            UserPermissionBusiness domain = UserPermissionBusinessMapper.entityToDomain(p);

            response.add(new UserPermissionBusinessResponse(domain));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
