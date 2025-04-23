package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.evento.CreateEventoCommand;
import com.angelldca.siga.application.port.in.command.evento.DeleteEventListUseCase;
import com.angelldca.siga.application.port.in.command.evento.DeleteListEventCommand;
import com.angelldca.siga.application.port.in.command.evento.UpdateEventCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.service.EventService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Evento;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/evento")
public class EventController {
    private final CreateUseCase<Evento, CreateEventoCommand> createUseCase;
    private final UpdateUseCase<Evento, UpdateEventCommand,Long> updateUseCase;
    private final DeleteUseCase<Evento,Long> deleteUseCase;
    private final DeleteEventListUseCase deleteEventListUseCase;
    private final GetUseCase<Long> getUseCase;
    private final ListUseCase listUseCase;

    public EventController(
            EventService eventService) {
        this.createUseCase = eventService;
        this.updateUseCase = eventService;
        this.deleteUseCase = eventService;
        this.getUseCase = eventService;
        this.listUseCase = eventService;
        this.deleteEventListUseCase = eventService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateEventoCommand command){
        Evento evento =  createUseCase.create(command);
        IResponse response = new Message<>(evento.getId(), "CREATE_EVENTO");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody UpdateEventCommand command){
        Evento evento =  updateUseCase.update(command, id);
        IResponse response = new Message<>(evento.getId(), "UPDATE_EVENTO");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Evento evento = deleteUseCase.delete(id);
        IResponse response = new Message<>(evento.getId(), "DELETE_EVENTO");
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        IResponse response = getUseCase.getById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageableUtil.createPageable(request);
        PaginatedResponse response = this.listUseCase.search(pageable, request.getFilter());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/delete-list")
    public ResponseEntity<?> deleteAll(@RequestBody DeleteListEventCommand command){
        deleteEventListUseCase.deleteListEvent(command);
        IResponse response = new Message<>(null, "DELETE_EVENTO_LIST");
        return ResponseEntity.ok(response);
    }
}
