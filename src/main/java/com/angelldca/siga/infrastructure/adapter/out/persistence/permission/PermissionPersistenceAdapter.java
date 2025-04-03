package com.angelldca.siga.infrastructure.adapter.out.persistence.permission;


import com.angelldca.siga.application.port.out.permission.LoadPermissionPort;
import com.angelldca.siga.application.port.out.permission.PermissionCRUDPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Permission;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.PermissionWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.PermissionReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


@Qualifier("permissionPersistenceAdapter")
@PersistenceAdapter
public class PermissionPersistenceAdapter implements PermissionCRUDPort, LoadPermissionPort {

    private final PermissionReadDataJPARepository query;
    private final PermissionWriteDataJPARepository command;
    private final PermissionMapper mapper;

    public PermissionPersistenceAdapter(PermissionReadDataJPARepository query, PermissionWriteDataJPARepository command, PermissionMapper mapper) {
        this.query = query;
        this.command = command;
        this.mapper = mapper;
    }

    @Override
    public Permission delete(Long id) {
        Permission domain = obtenerPorId(id);
        PermissionEntity entity = mapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public Permission obtenerPorId(Long id) {
        Permission entity = this.query.findById(id)
                .map(mapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Permisos"));
        return entity;
    }

    @Override
    public Page<PermissionEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Permission save(Permission domain) {
        PermissionEntity entity = this.command.save(mapper.domainToEntity(domain));
        return mapper.entityToDomain(entity);
    }

    @Override
    public List<Permission> loadAllByIds(List<Long> ids) {
        List<PermissionEntity> entities = query.findAllById(ids);
        return entities.stream()
                .map(mapper::entityToDomain)
                .toList();
    }
}
