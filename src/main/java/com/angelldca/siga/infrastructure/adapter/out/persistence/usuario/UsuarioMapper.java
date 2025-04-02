package com.angelldca.siga.infrastructure.adapter.out.persistence.usuario;

import com.angelldca.siga.domain.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    User entityToDomain(UserEntity entity);
    UserEntity domainToEntity(User usuario);
}
