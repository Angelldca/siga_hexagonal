package com.angelldca.siga.infrastructure.adapter.out.persistence.business_module;


import com.angelldca.siga.application.port.out.businessModule.BusinessModuleCRUDPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.BusinessModule;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.BusinessModuleWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.BusinessModuleReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@PersistenceAdapter
@Qualifier("businessModulePersistenceAdapter")
public class BusinessModulePersistenceAdapter implements BusinessModuleCRUDPort {

    private final BusinessModuleWriteDataJPARepository command;
    private final BusinessModuleReadDataJPARepository query;
    private final BusinessModuleMapper mapper;

    public BusinessModulePersistenceAdapter(BusinessModuleWriteDataJPARepository command, BusinessModuleReadDataJPARepository query, BusinessModuleMapper mapper) {
        this.command = command;
        this.query = query;
        this.mapper = mapper;
    }

    @Override
    public BusinessModule delete(UUID id) {
        BusinessModule domain = obtenerPorId(id);
        BusinessModuleEntity entity = mapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public BusinessModule obtenerPorId(UUID id) {
        BusinessModule entity = this.query.findById(id)
                .map(mapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","BusinessModule"));
        return entity;
    }

    @Override
    public Page<BusinessModuleEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public BusinessModule save(BusinessModule domain) {
        BusinessModuleEntity entity = this.command.save(mapper.domainToEntity(domain));
        return mapper.entityToDomain(entity);
    }

}
