package com.angelldca.siga.infrastructure.adapter.out.persistence.usuario;

import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",uses = {
        UserPermissionBusinessMapper.class
})
public interface UsuarioMapper {
    @Mapping(target = "id", source = "id")
    User entityToDomain(UserEntity entity);
    UserEntity domainToEntity(User usuario);
}
