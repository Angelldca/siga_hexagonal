package com.angelldca.siga.infrastructure.adapter.out.persistence.manual;


import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.application.port.out.manual.GetAllByNamePort;
import com.angelldca.siga.application.port.out.manual.GetByExcatNamePort;
import com.angelldca.siga.application.port.out.manual.GetByNombrePort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Manual;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoMapper;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.ManualWriteDataJPARespository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.ManualReadDataJPARespository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@Qualifier("manualPersistenceAdapter")
public class ManualPersistenceAdapter  implements
        DeletePort<Manual,Long>,
        GetPort<Manual,Long>,
        ListPort<ManualEntity>, SavePort<Manual>, GetAllByNamePort, GetByNombrePort, GetByExcatNamePort {

    private final ManualReadDataJPARespository query;
    private final ManualWriteDataJPARespository command;

    public ManualPersistenceAdapter(ManualReadDataJPARespository query,ManualWriteDataJPARespository command) {
       this.query = query;
       this.command = command;
    }


    @Override
    public Manual delete(Long id) {
        Manual domain = obtenerPorId(id);
        ManualEntity entity = ManualMapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public Manual obtenerPorId(Long id) {
        Manual entity = this.query.findById(id)
                .map(ManualMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Manual"));
        return entity;
    }

    @Override
    public Page<ManualEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Manual save(Manual domain) {
        ManualEntity entity = this.command.save(ManualMapper.domainToEntity(domain));
        return ManualMapper.entityToDomain(entity);
    }

    @Override
    public Optional<ManualEntity> obtenerPorNombreExacto(String nombre) {
        return query.findByNombre(nombre);
    }

    @Override
    public List<ManualEntity> buscarPorNombreAproximado(String nombreParcial) {
        return query.findByNombreContainingIgnoreCase(nombreParcial);
    }

    @Override
    public List<String> obtenerTodosLosNombres() {
        return query.findAllNombres();
    }

}
