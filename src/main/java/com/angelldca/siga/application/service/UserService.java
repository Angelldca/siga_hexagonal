package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.user.CreateUserCommand;
import com.angelldca.siga.application.port.in.command.user.UpdateUserCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.user.UserCrudPort;
import com.angelldca.siga.application.port.out.user_permission_business.LoadUserPermissionBusinessPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.*;
import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.domain.model.UserPermissionBusiness;

import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UserEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@UseCase
public class UserService implements
        CreateUseCase<User, CreateUserCommand>,
        UpdateUseCase<User, UpdateUserCommand, UUID>,
        DeleteUseCase<User,UUID>,
        GetUseCase<UUID>,
        ListUseCase {
    private final UserCrudPort crudPort;
    private final LoadUserPermissionBusinessPort loadUserPermissionBusinessPort;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserService(@Qualifier("userPersistenceAdapter")UserCrudPort userCrudPort, LoadUserPermissionBusinessPort loadUserPermissionBusinessPort) {
        this.crudPort = userCrudPort;
        this.loadUserPermissionBusinessPort = loadUserPermissionBusinessPort;

    }


    @Override
    public User create(CreateUserCommand command) {
        List<UserPermissionBusiness> userPermissionBusinesses = this.loadUserPermissionBusinessPort.loadAllByIds(command.getUserPermissionBusinesses());

        User domain = new User(
                UUID.randomUUID(),
                command.getUsername(),
                command.getEmail(),
                passwordEncoder.encode(command.getPassword()),
                command.getImage(),
                command.getType(),
                 Collections.emptyList()//userPermissionBusinesses

        );
        return this.crudPort.save(domain);
    }

    @Override
    public User delete(UUID id) {
        return this.crudPort.delete(id);
    }

    @Override
    public User update(UpdateUserCommand command, UUID id) {
        User domain = this.crudPort.obtenerPorId(id);
        List<UserPermissionBusiness> userPermissionBusinesses = this.loadUserPermissionBusinessPort.loadAllByIds(command.getUserPermissionBusinesses());

        domain.setEmail(command.getEmail());
        domain.setType(command.getType());
        domain.setUsername(command.getUsername());
        domain.setImage(command.getImage());
        domain.setUserPermissionBusinesses(userPermissionBusinesses);
        return this.crudPort.save(domain);
    }

    @Override
    public IResponse getById(UUID id) {
        User domain = this.crudPort.obtenerPorId(id);
        return new UserResponse(domain);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<UserEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<UserEntity> data = this.crudPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<UserEntity> data) {
        List<UserResponseSearch> response = new ArrayList<>();
        for (UserEntity p : data.getContent()) {
            response.add(new UserResponseSearch(UsuarioMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
