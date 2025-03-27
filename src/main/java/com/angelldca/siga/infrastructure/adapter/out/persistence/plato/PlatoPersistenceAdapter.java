package com.angelldca.siga.infrastructure.adapter.out.persistence.plato;


import com.angelldca.siga.application.port.out.DeletePlatoPort;
import com.angelldca.siga.application.port.out.GetPlatoPort;
import com.angelldca.siga.application.port.out.ListPlatosPort;
import com.angelldca.siga.application.port.out.SavePlatoPort;
import com.angelldca.siga.common.PersistenceAdapter;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.PlatoWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.PlatoReadDataJPARepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@PersistenceAdapter
public class PlatoPersistenceAdapter implements DeletePlatoPort, GetPlatoPort, ListPlatosPort, SavePlatoPort {

   private final PlatoReadDataJPARepository query;
   private final PlatoWriteDataJPARepository command;

    public PlatoPersistenceAdapter(PlatoReadDataJPARepository query, PlatoWriteDataJPARepository command) {
        this.query = query;
        this.command = command;
    }


    @Override
    public void delete(Long id) { //TODO: Excepciones
        Optional<PlatoEntity> entity = query.findById(id);
        this.command.delete(entity.get());

    }

    @Override
    public Plato obtenerPorId(Long id) {
        Plato entity = this.query.findById(id)
                .map(PlatoMapper::entityToDomain)
                .orElseThrow(RuntimeException::new);
        return entity;
    }

    @Override
    public List<Plato> listar() {
        return Collections.emptyList();
    }

    @Override
    public Plato save(Plato plato) {
        PlatoEntity entity = this.command.save(PlatoMapper.domainToEntity(plato));
       return PlatoMapper.entityToDomain(entity);
    }
}
