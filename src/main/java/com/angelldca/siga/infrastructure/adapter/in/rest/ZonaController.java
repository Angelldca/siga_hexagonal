package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;

import com.angelldca.siga.application.port.in.command.zona.CreateZonaCommand;
import com.angelldca.siga.application.port.in.command.zona.UpdateZonaCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;

import com.angelldca.siga.application.service.ZonaService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;

import com.angelldca.siga.domain.model.Zona;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@WebAdapter
@RestController
@RequestMapping("/api/zona")
public class ZonaController {
    private final CreateUseCase<Zona, CreateZonaCommand> createUseCase;
    private final UpdateUseCase<Zona, UpdateZonaCommand, Long> updateUseCase;
    private final DeleteUseCase<Zona,Long> deleteUseCase;
    private final GetUseCase<Long> getUseCase;
    private final ListUseCase listUseCase;

    public ZonaController(ZonaService service) {
        this.createUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
        this.getUseCase = service;
        this.listUseCase = service;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateZonaCommand command){
        Zona zona =  createUseCase.create(command);
        IResponse response = new Message<>(zona.getId(), "CREATE_ZONA");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody UpdateZonaCommand command){
        Zona zona =  updateUseCase.update(command, id);
        IResponse response = new Message<>(zona.getId(), "UPDATE_ZONA");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Zona zona = deleteUseCase.delete(id);
        IResponse response = new Message<>(zona.getId(), "DELETE_ZONA");
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
}
