package com.angelldca.siga.infrastructure.adapter.out.persistence.plato;


import com.angelldca.siga.application.port.out.*;
import com.angelldca.siga.application.port.out.plato.LoadPlatosPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.*;
import com.angelldca.siga.domain.model.Dpersona;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.PlatoWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.PlatoReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;


@PersistenceAdapter
@Qualifier("platoPersistenceAdapter")
public class PlatoPersistenceAdapter implements
        DeletePort<Plato,Long>, GetPort<Plato,Long>,
        ListPort<PlatoEntity>, SavePort<Plato>, LoadPlatosPort, DeleteListPort<Long> {

   private final PlatoReadDataJPARepository query;
   private final PlatoWriteDataJPARepository command;


    public PlatoPersistenceAdapter(PlatoReadDataJPARepository query, PlatoWriteDataJPARepository command) {
        this.query = query;
        this.command = command;

    }


    @Override
    public Plato delete(Long id) {
        Plato plato = obtenerPorId(id);
        PlatoEntity entity = PlatoMapper.domainToEntity(plato);
        this.command.delete(entity);
        return plato;
    }

    @Override
    public Plato obtenerPorId(Long id) {
        Plato entity = this.query.findById(id)
                .map(PlatoMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Plato"));
        return entity;
    }





    @Override
    public Page<PlatoEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Plato save(Plato plato) {
        PlatoEntity entity = this.command.save(PlatoMapper.domainToEntity(plato));
        return PlatoMapper.entityToDomain(entity);
    }

    @Override
    public List<Plato> loadAllByIds(List<Long> ids) {
        List<PlatoEntity> entities = query.findAllById(ids);
        return entities.stream()
                .map(PlatoMapper::entityToDomain)
                .toList();

    }

    @Override
    public void deleteList(List<Long> id) {
        List<Plato> platos = id.stream()
                .map(this::obtenerPorId)
                .toList();

        platos.forEach(p -> {
            p.setIsDelete(true);
            this.save(p);
        });
    }
}
