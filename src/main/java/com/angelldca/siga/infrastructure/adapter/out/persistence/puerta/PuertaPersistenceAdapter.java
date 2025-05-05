package com.angelldca.siga.infrastructure.adapter.out.persistence.puerta;


import com.angelldca.siga.application.port.out.DeleteListPort;
import com.angelldca.siga.application.port.out.puerta.PuertaCrudPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Puerta;
import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.PuertaWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.PuertaReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@PersistenceAdapter
@Qualifier("puertaPersistenceAdapter")
public class PuertaPersistenceAdapter implements PuertaCrudPort, DeleteListPort<Long> {

    private final PuertaReadDataJPARepository query;
    private final PuertaWriteDataJPARepository command;

    public PuertaPersistenceAdapter(PuertaReadDataJPARepository query, PuertaWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public Puerta delete(Long id) {
        Puerta domain = obtenerPorId(id);
        PuertaEntity entity = PuertaMapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public Puerta obtenerPorId(Long id) {
        Puerta entity = this.query.findById(id)
                .map(PuertaMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Puerta"));
        return entity;
    }

    @Override
    public Page<PuertaEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Puerta save(Puerta domain) {
        PuertaEntity entity = this.command.save(PuertaMapper.domainToEntity(domain));
        return PuertaMapper.entityToDomain(entity);
    }

    @Override
    public void deleteList(List<Long> id) {
        List<Puerta> puertas = id.stream()
                .map(this::obtenerPorId)
                .toList();

        puertas.forEach(p -> {
            p.setIsDelete(true);
            this.save(p);
        });
    }
}
