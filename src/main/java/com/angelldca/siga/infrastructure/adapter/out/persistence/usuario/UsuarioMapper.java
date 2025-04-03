package com.angelldca.siga.infrastructure.adapter.out.persistence.usuario;

import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


public class UsuarioMapper {

    public static User entityToDomain(UserEntity entity) {
        if (entity == null) return null;

        User domain = new User();
        domain.setId(entity.getId());
        domain.setUsername(entity.getUsername());
        domain.setEmail(entity.getEmail());
        domain.setPassword(entity.getPassword());
        domain.setImage(entity.getImage());
        domain.setType(entity.getType());
        // Si necesitas los permisos, mapea con UserPermissionBusinessMapper aquí.
        return domain;
    }

    public static UserEntity domainToEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setUsername(domain.getUsername());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setImage(domain.getImage());
        entity.setType(domain.getType());
        return entity;
    }
}