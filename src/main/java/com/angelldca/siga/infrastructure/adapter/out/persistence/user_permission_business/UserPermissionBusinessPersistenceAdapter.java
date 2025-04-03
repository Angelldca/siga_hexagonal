package com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business;


import com.angelldca.siga.application.port.out.user_permission_business.LoadUserPermissionBusinessPort;
import com.angelldca.siga.application.port.out.user_permission_business.UserPermissionBusinessCRUDPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.UserPermissionBusiness;

import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UsuarioMapper;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.UserBusinessPermissionWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.UserBusinessPermissionReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

@Qualifier("UserPermissionBusinessPersistenceAdapter")
@PersistenceAdapter
public class UserPermissionBusinessPersistenceAdapter implements
        UserPermissionBusinessCRUDPort, LoadUserPermissionBusinessPort {

    private final UserBusinessPermissionReadDataJPARepository query;
    private final UserBusinessPermissionWriteDataJPARepository command;
    private final UserPermissionBusinessMapper mapper;
    private final UsuarioMapper usuarioMapper;

    public UserPermissionBusinessPersistenceAdapter(UserBusinessPermissionReadDataJPARepository query, UserBusinessPermissionWriteDataJPARepository command, UserPermissionBusinessMapper mapper, UsuarioMapper usuarioMapper) {
        this.query = query;
        this.command = command;
        this.mapper = mapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UserPermissionBusiness delete(UUID id) {
        UserPermissionBusiness domain = obtenerPorId(id);
        UserPermissionBusinessEntity entity = mapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public UserPermissionBusiness obtenerPorId(UUID id) {
        UserPermissionBusinessEntity entity = this.query.findById(id)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id", "Permisos de Usuario por Empresa"));

        UserPermissionBusiness domain = mapper.entityToDomain(entity);
        domain.setUser(usuarioMapper.entityToDomain(entity.getUser()));

        return domain;
    }

    @Override
    public Page<UserPermissionBusinessEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public UserPermissionBusiness save(UserPermissionBusiness domain) {
        UserPermissionBusinessEntity entityToSave = mapper.domainToEntity(domain);
        entityToSave.setUser(usuarioMapper.domainToEntity(domain.getUser()));
        UserPermissionBusinessEntity savedEntity = this.command.save(entityToSave);
        UserPermissionBusiness result = mapper.entityToDomain(savedEntity);
        result.setUser(usuarioMapper.entityToDomain(savedEntity.getUser()));
        return result;
    }

    @Override
    public List<UserPermissionBusiness> loadAllByIds(List<UUID> ids) {
        List<UserPermissionBusinessEntity> entities = query.findAllById(ids);

        return entities.stream()
                .map(entity -> {
                    UserPermissionBusiness domain = mapper.entityToDomain(entity);
                    domain.setUser(usuarioMapper.entityToDomain(entity.getUser()));
                    return domain;
                })
                .toList();
    }
}
