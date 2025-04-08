package com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona;


import com.angelldca.siga.application.port.out.puerta_persona.PuertaPersonaCrudPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.PuertaPersona;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.PuertaPersonaWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.PuertaPersonaReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@PersistenceAdapter
@Qualifier("puertaPersonaPersistenceAdapter")
public class PuertaPersonaPersistenceAdapter implements PuertaPersonaCrudPort {

    private final PuertaPersonaReadDataJPARepository query;
    private final PuertaPersonaWriteDataJPARepository command;

    public PuertaPersonaPersistenceAdapter(PuertaPersonaReadDataJPARepository query, PuertaPersonaWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public PuertaPersona delete(UUID id) {
        PuertaPersona domain = obtenerPorId(id);
        PuertaPersonaEntity entity = PuertaPersonaMapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public PuertaPersona obtenerPorId(UUID id) {
        PuertaPersona entity = this.query.findById(id)
                .map(PuertaPersonaMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","PuertaPersona"));
        return entity;
    }

    @Override
    public Page<PuertaPersonaEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public PuertaPersona save(PuertaPersona domain) {
        PuertaPersonaEntity entity = this.command.save(PuertaPersonaMapper.domainToEntity(domain));
        return PuertaPersonaMapper.entityToDomain(entity);
    }
}
