package com.angelldca.siga.infrastructure.adapter.out.persistence.acceso;


import com.angelldca.siga.application.port.out.acceso.AccesoCrudPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Acceso;

import com.angelldca.siga.infrastructure.adapter.out.repository.command.AccesoWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.AccesoReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@PersistenceAdapter
@Qualifier("accesoPersonaPersistenceAdapter")
public class AccesoPersistenceAdapter implements AccesoCrudPort {
    private final AccesoReadDataJPARepository query;
    private final AccesoWriteDataJPARepository command;

    public AccesoPersistenceAdapter(AccesoReadDataJPARepository query, AccesoWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public Acceso delete(Long id) {
        Acceso domain = obtenerPorId(id);
        AccesoEntity entity = AccesoMapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public Acceso obtenerPorId(Long id) {
        Acceso entity = this.query.findById(id)
                .map(AccesoMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Acceso"));
        return entity;
    }

    @Override
    public Page<AccesoEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Acceso save(Acceso domain) {
        AccesoEntity entity = this.command.save(AccesoMapper.domainToEntity(domain));
        return AccesoMapper.entityToDomain(entity);
    }
}
