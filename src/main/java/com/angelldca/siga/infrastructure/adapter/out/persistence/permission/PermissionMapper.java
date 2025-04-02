package com.angelldca.siga.infrastructure.adapter.out.persistence.permission;


import com.angelldca.siga.domain.model.Permission;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ModuleMapper.class)
public interface PermissionMapper {
    Permission entityToDomain(PermissionEntity entity);

    PermissionEntity domainToEntity(Permission domain);
}
