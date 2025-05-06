package com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona;


import com.angelldca.siga.application.port.out.DeleteListPort;
import com.angelldca.siga.application.port.out.persona.PersonaCrudPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Dpersona;
import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.PersonaWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.PersonaReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@PersistenceAdapter
@Qualifier("personaPersistenceAdapter")
public class PersonaPersistenceAdapter implements PersonaCrudPort, DeleteListPort<Long> {

    private final PersonaReadDataJPARepository query;
    private final PersonaWriteDataJPARepository command;

    public PersonaPersistenceAdapter(PersonaReadDataJPARepository query, PersonaWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public Dpersona delete(Long id) {
        Dpersona domain = obtenerPorId(id);
        DpersonaEntity entity = DpersonaMapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public Dpersona obtenerPorId(Long id) {
        Dpersona entity = this.query.findById(id)
                .map(DpersonaMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Persona"));
        return entity;
    }

    @Override
    public Page<DpersonaEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Dpersona save(Dpersona domain) {
        DpersonaEntity entity = this.command.save(DpersonaMapper.domainToEntity(domain));
        return DpersonaMapper.entityToDomain(entity);
    }

    @Override
    public void deleteList(List<Long> id) {
        List<Dpersona> dpersonas = id.stream()
                .map(this::obtenerPorId)
                .toList();

        dpersonas.forEach(p -> {
            p.setIsDelete(true);
            this.save(p);
        });

    }
}
