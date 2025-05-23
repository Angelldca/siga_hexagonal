package com.angelldca.siga.infrastructure.adapter.out.persistence.Evento;


import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.application.port.out.evento.CheckEventUniquePort;
import com.angelldca.siga.application.port.out.evento.DeleteEventListPort;
import com.angelldca.siga.application.port.out.evento.GetAllEventById;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Evento;

import com.angelldca.siga.domain.model.Puerta;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.EventWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.EventReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@PersistenceAdapter
@Qualifier("eventPersistenceAdapter")
public class EventPersistenceAdapter implements
        DeletePort<Evento,Long>, GetPort<Evento,Long>, GetAllEventById,
        ListPort<EventoEntity>, SavePort<Evento>,
        CheckEventUniquePort, DeleteEventListPort {

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
    public boolean existsByNameAndDateRange(String name, LocalDate fechaInicio, LocalDate fechaFin, Object excludeId) {
        Long id = (excludeId instanceof Long) ? (Long) excludeId : null;
        return query.existsByNombreAndFechaInicioAndFechaFinAndIdNot(name, fechaInicio, fechaFin, id);

    }

    @Override
    public void deleteListEvent(List<Long> ids) {
        List<Evento> e = ids.stream()
                .map(this::obtenerPorId)
                .toList();

        e.forEach(p -> {
            p.setIsDelete(true);
            this.save(p);
        });
    }

    @Override
    public List<Evento> getAllById(List<Long> ids) {
        List<EventoEntity> entities = query.findAllById(ids);

        if (entities.size() != ids.size()) {
            List<Long> foundIds = entities.stream().map(EventoEntity::getId).toList();
            List<Long> missing = ids.stream().filter(id -> !foundIds.contains(id)).toList();
            throw  BusinessExceptionFactory.objectNotFound("id","Evento"); //TODO mejorar excepcion
        }

        return entities.stream()
                .map(EventoMapper::entityToDomain)
                .toList();
    }
}
