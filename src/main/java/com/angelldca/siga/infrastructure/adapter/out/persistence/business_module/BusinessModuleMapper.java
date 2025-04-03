package com.angelldca.siga.infrastructure.adapter.out.persistence.business_module;


import com.angelldca.siga.domain.model.BusinessModule;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaMapper;
import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleMapper;

import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public class BusinessModuleMapper {

    public static BusinessModule entityToDomain(BusinessModuleEntity entity) {
        if (entity == null) return null;

        BusinessModule domain = new BusinessModule();
        domain.setId(entity.getId());
        domain.setBusiness(EmpresaMapper.entityToDomain(entity.getBusiness()));
        domain.setModule(ModuleMapper.entityToDomain(entity.getModule()));
        return domain;
    }

    public static BusinessModuleEntity domainToEntity(BusinessModule domain) {
        if (domain == null) return null;

        BusinessModuleEntity entity = new BusinessModuleEntity();
        entity.setId(domain.getId());
        entity.setBusiness(EmpresaMapper.domainToEntity(domain.getBusiness()));
        entity.setModule(ModuleMapper.domainToEntity(domain.getModule()));
        return entity;
    }
}