package com.angelldca.siga.infrastructure.adapter.out.persistence.empresa;



import com.angelldca.siga.application.port.out.business.BusinessCRUDPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.BusinessWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.BusinessReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;


@PersistenceAdapter
@Qualifier("empresaPersistenceAdapter")
public class EmpresaPersistenceAdapter implements BusinessCRUDPort {

    private final BusinessReadDataJPARepository query;
    private final BusinessWriteDataJPARepository command;
    private final EmpresaMapper mapper;

    public EmpresaPersistenceAdapter(BusinessReadDataJPARepository query, BusinessWriteDataJPARepository command, EmpresaMapper mapper) {
        this.query = query;
        this.command = command;
        this.mapper = mapper;
    }

    @Override
    public Empresa delete(UUID id) {
        Empresa domain = obtenerPorId(id);
        EmpresaEntity entity = mapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public Empresa obtenerPorId(UUID id) {
        Empresa entity = this.query.findById(id)
                .map(mapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Empresa"));
        return entity;
    }

    @Override
    public Page<EmpresaEntity> list(Specification specifications, Pageable pageable) {

        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Empresa save(Empresa domain) {
        EmpresaEntity entity = this.command.save(mapper.domainToEntity(domain));
        return mapper.entityToDomain(entity);
    }
}
