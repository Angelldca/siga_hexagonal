package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteListUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;

import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.persona.CreatePersonaCommand;
import com.angelldca.siga.application.port.in.command.persona.UpdatePersonacommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.DeleteListCommand;
import com.angelldca.siga.application.service.PersonaService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Dpersona;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/persona")
public class PersonaController {
    private final CreateUseCase<Dpersona, CreatePersonaCommand> createUseCase;
    private final UpdateUseCase<Dpersona, UpdatePersonacommand,Long> updateUseCase;
    private final DeleteUseCase<Dpersona,Long> deleteUseCase;
    private final GetUseCase<Long> getUseCase;
    private final ListUseCase listUseCase;
    private final DeleteListUseCase<Long> deleteListUseCase;

    public PersonaController(PersonaService service) {
        this.createUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
        this.getUseCase = service;
        this.listUseCase = service;
        this.deleteListUseCase = service;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePersonaCommand command){
        Dpersona dpersona =  createUseCase.create(command);
        IResponse response = new Message<>(dpersona.getId(), "CREATE_PERSONA");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody UpdatePersonacommand command){
        Dpersona dpersona =  updateUseCase.update(command, id);
        IResponse response = new Message<>(dpersona.getId(), "UPDATE_PERSONA");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Dpersona dpersona = deleteUseCase.delete(id);
        IResponse response = new Message<>(dpersona.getId(), "DELETE_PERSONA");
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
    public ResponseEntity<?> deleteAll(@RequestBody DeleteListCommand<Long> command){
        deleteListUseCase.deleteList(command);
        IResponse response = new Message<>(null, "DELETE_LIST");
        return ResponseEntity.ok(response);
    }
}
