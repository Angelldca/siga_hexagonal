package com.angelldca.siga.infrastructure.adapter.out.persistence.permission;


import com.angelldca.siga.domain.model.Permission;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessMapper;

public class PermissionMapper {

    public static Permission entityToDomain(PermissionEntity entity) {
        if (entity == null) return null;

        Permission domain = new Permission();
        domain.setId(entity.getId());
        domain.setCode(entity.getCode());
        domain.setDescription(entity.getDescription());
        domain.setAction(entity.getAction());
        domain.setModule(ModuleMapper.entityToDomain(entity.getModule()));
        return domain;
    }

    public static PermissionEntity domainToEntity(Permission domain) {
        if (domain == null) return null;

        PermissionEntity entity = new PermissionEntity();
        entity.setId(domain.getId());
        entity.setCode(domain.getCode());
        entity.setDescription(domain.getDescription());
        entity.setAction(domain.getAction());
        entity.setModule(ModuleMapper.domainToEntity(domain.getModule()));
        return entity;
    }
}