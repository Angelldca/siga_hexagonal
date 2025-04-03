package com.angelldca.siga.infrastructure.adapter.out.persistence.Evento;


import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.application.port.out.evento.CheckEventUniquePort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Evento;

import com.angelldca.siga.infrastructure.adapter.out.repository.command.EventWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.EventReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@PersistenceAdapter
@Qualifier("eventPersistenceAdapter")
public class EventPersistenceAdapter implements
        DeletePort<Evento,Long>, GetPort<Evento,Long>,
        ListPort<EventoEntity>, SavePort<Evento>, CheckEventUniquePort {

    private final EventReadDataJPARepository query;
    private final EventWriteDataJPARepository command;


    public EventPersistenceAdapter(EventReadDataJPARepository query, EventWriteDataJPARepository command) {
        this.query = query;
        this.command = command;

    }

    @Override
    public Evento delete(Long id) {
        Evento evento = obtenerPorId(id);
        EventoEntity entity = EventoMapper.domainToEntity(evento);
        this.command.delete(entity);
        return evento;
    }

    @Override
    public Evento obtenerPorId(Long id) {
        Evento entity = this.query.findById(id)
                .map(EventoMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Evento"));
        return entity;
    }

    @Override
    public Page list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Evento save(Evento evento) {
        EventoEntity entity = this.command.save(EventoMapper.domainToEntity(evento));
        return EventoMapper.entityToDomain(entity);
    }

    @Override
    public boolean existsByNameAndDateRange(String name, LocalDateTime fechaInicio, LocalDateTime fechaFin, Object excludeId) {
        Long id = (excludeId instanceof Long) ? (Long) excludeId : null;
        return query.existsByNombreAndFechaInicioAndFechaFinAndIdNot(name, fechaInicio, fechaFin, id);

    }
}
