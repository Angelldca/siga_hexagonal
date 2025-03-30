package com.angelldca.siga.infrastructure.adapter.out.persistence.plato;


import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.*;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.PlatoWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.PlatoReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;




@PersistenceAdapter
@Qualifier("platoPersistenceAdapter")
public class PlatoPersistenceAdapter implements
        DeletePort<Plato>, GetPort<Plato>,
        ListPort<PlatoEntity>, SavePort<Plato> {

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
}
