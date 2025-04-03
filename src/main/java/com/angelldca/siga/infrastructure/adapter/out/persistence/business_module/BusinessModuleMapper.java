package com.angelldca.siga.infrastructure.adapter.out.persistence.business_module;


import com.angelldca.siga.domain.model.BusinessModule;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleMapper;

import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ModuleMapper.class,
        EmpresaMapper.class
})
public interface BusinessModuleMapper {
    @Mapping(target = "id", source = "id")
    BusinessModule entityToDomain(BusinessModuleEntity entity);

    BusinessModuleEntity domainToEntity(BusinessModule domain);
}
