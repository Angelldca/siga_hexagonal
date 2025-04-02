package com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business;


import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.permission.PermissionMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        PermissionMapper.class,
        EmpresaMapper.class
})
public interface UserPermissionBusinessMapper {
    UserPermissionBusiness entityToDomain(UserPermissionBusinessEntity entity);

    UserPermissionBusinessEntity domainToEntity(UserPermissionBusiness domain);
}
