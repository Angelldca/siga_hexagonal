package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.evento.CreateEventoCommand;
import com.angelldca.siga.application.port.in.command.evento.DeleteEventListUseCase;
import com.angelldca.siga.application.port.in.command.evento.DeleteListEventCommand;
import com.angelldca.siga.application.port.in.command.evento.UpdateEventCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.application.port.out.evento.CheckEventUniquePort;
import com.angelldca.siga.application.port.out.evento.DeleteEventListPort;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.criteria.FilterCriteria;
import com.angelldca.siga.common.response.EventoResponse;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.PaginatedResponse;

import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Evento;

import com.angelldca.siga.domain.rule.RulesChecker;
import com.angelldca.siga.domain.rule.evento.EventNameMustBeUnique;
import com.angelldca.siga.domain.rule.evento.EventNameNotNullRule;
import com.angelldca.siga.domain.rule.evento.EventRangeDateValid;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoMapper;

import com.angelldca.siga.infrastructure.adapter.out.persistence.specification.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UseCase
@Qualifier("eventService")
public class EventService implements
        CreateUseCase<Evento, CreateEventoCommand>,
        UpdateUseCase<Evento, UpdateEventCommand,Long>,
        DeleteUseCase<Evento,Long>,
        GetUseCase<Long>,
        DeleteEventListUseCase,
        ListUseCase {

    private final DeletePort<Evento,Long> deletePort;
    private final GetPort<Evento,Long> getPort;
    private final GetPort<Empresa, UUID> getPortEmpresa;
    private final ListPort<EventoEntity> listPort;
    private final SavePort<Evento> savePort;
    private final DeleteEventListPort deleteEventListPort;
    private final CheckEventUniquePort checkEventUniquePort;


    public EventService(
            @Qualifier("eventPersistenceAdapter") DeletePort<Evento,Long> deletePort,
            @Qualifier("eventPersistenceAdapter") GetPort<Evento,Long> getPort,
            GetPort<Empresa, UUID> getPortEmpresa, @Qualifier("eventPersistenceAdapter") ListPort<EventoEntity> listPort,
            @Qualifier("eventPersistenceAdapter") SavePort<Evento> savePort, DeleteEventListPort deleteEventListPort, CheckEventUniquePort checkEventUniquePort) {
        this.deletePort = deletePort;
        this.getPort = getPort;
        this.getPortEmpresa = getPortEmpresa;
        this.listPort = listPort;
        this.savePort = savePort;
        this.deleteEventListPort = deleteEventListPort;
        this.checkEventUniquePort = checkEventUniquePort;

    }

    @Override
    public Evento create(CreateEventoCommand command) {
        /*
          RulesChecker.checkRule(new EventNameNotNullRule(command.getNombre()));
        RulesChecker.checkRule(new EventNameMustBeUnique<>(
                command.getNombre(),
                command.getFechaInicio(),
                command.getFechaFin(),
                0L,
                checkEventUniquePort
        ));
        RulesChecker.checkRule(new EventRangeDateValid(command.getFechaInicio(),command.getFechaFin()));

        * */

       Empresa empresa = this.getPortEmpresa.obtenerPorId(command.getEmpresa());
        Evento entity = new Evento(
                null,
                command.getNombre(),
                command.getFechaInicio(),
                command.getFechaFin(),
                command.getHoraInicio(),
                command.getHoraFin(),
                command.getActivo(),
                command.getIlimitado(),
                false,
                command.getType(),
                empresa
        );

        return this.savePort.save(entity);
    }

    @Override
    public Evento delete(Long id) {

        return this.deletePort.delete(id);
    }

    @Override
    public Evento update(UpdateEventCommand command, Long id) {
        /*
        RulesChecker.checkRule(new EventNameNotNullRule(command.getNombre()));
        RulesChecker.checkRule(new EventNameMustBeUnique<>(
                command.getNombre(),
                command.getFechaInicio(),
                command.getFechaFin(),
                id,
                checkEventUniquePort
        ));
        RulesChecker.checkRule(new EventRangeDateValid(command.getFechaInicio(),command.getFechaFin()));


        * */

        Evento entity =  this.getPort.obtenerPorId(id);
        entity.setNombre(command.getNombre());
        entity.setFechaInicio(command.getFechaInicio());
        entity.setFechaFin(command.getFechaFin());
        entity.setHoraInicio(command.getHoraInicio());
        entity.setHoraFin(command.getHoraFin());
        entity.setActivo(command.getActivo());
        entity.setIlimitado(command.getIlimitado());
        entity.setType(command.getType());
        return this.savePort.save(entity);
    }

    @Override
    public IResponse getById(Long id) {
        Evento entity =  this.getPort.obtenerPorId(id);
        return new EventoResponse(entity);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<EventoEntity> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<EventoEntity> data = this.listPort.list(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<EventoEntity> data) {
        List<EventoResponse> eventoResponse = new ArrayList<>();
        for (EventoEntity p : data.getContent()) {
            eventoResponse.add(new EventoResponse(EventoMapper.entityToDomain(p)));
        }
        return new PaginatedResponse(eventoResponse, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void deleteListEvent(DeleteListEventCommand command) {
        this.deleteEventListPort.deleteListEvent(command.getIds());
    }
}
