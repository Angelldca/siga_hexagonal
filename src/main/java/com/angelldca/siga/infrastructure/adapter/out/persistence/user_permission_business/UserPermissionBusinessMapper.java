package com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business;


import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.permission.PermissionMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UsuarioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public class UserPermissionBusinessMapper {

    public static UserPermissionBusiness entityToDomain(UserPermissionBusinessEntity entity) {
        if (entity == null) return null;

        UserPermissionBusiness domain = new UserPermissionBusiness();
        domain.setId(entity.getId());
        domain.setUser(UsuarioMapper.entityToDomain(entity.getUser()));
        domain.setPermission(PermissionMapper.entityToDomain(entity.getPermission()));
        domain.setEmpresa(EmpresaMapper.entityToDomain(entity.getBusiness()));
        return domain;
    }

    public static UserPermissionBusinessEntity domainToEntity(UserPermissionBusiness domain) {
        if (domain == null) return null;

        UserPermissionBusinessEntity entity = new UserPermissionBusinessEntity();
        entity.setId(domain.getId());
        entity.setUser(UsuarioMapper.domainToEntity(domain.getUser()));
        entity.setPermission(PermissionMapper.domainToEntity(domain.getPermission()));
        entity.setBusiness(EmpresaMapper.domainToEntity(domain.getEmpresa()));
        return entity;
    }
}