package com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business;


import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.permission.PermissionMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UsuarioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        //UsuarioMapper.class,
        //PermissionMapper.class,
        EmpresaMapper.class
})
public interface UserPermissionBusinessMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "permission", ignore = true)
    UserPermissionBusiness entityToDomain(UserPermissionBusinessEntity entity);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "permission", ignore = true)
    UserPermissionBusinessEntity domainToEntity(UserPermissionBusiness domain);
}
