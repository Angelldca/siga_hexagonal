package com.angelldca.siga.infrastructure.adapter.out.persistence.permission;


import com.angelldca.siga.domain.model.Permission;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ModuleMapper.class,
        UserPermissionBusinessMapper.class
})
public interface PermissionMapper {
    @Mapping(target = "id", source = "id")
    Permission entityToDomain(PermissionEntity entity);


    @Mapping(target = "id", source = "id")
    PermissionEntity domainToEntity(Permission domain);
}
