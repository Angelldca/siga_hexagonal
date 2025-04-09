package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.Puerta.CreatePuertaCommand;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;

import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.service.PuertaService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;

import com.angelldca.siga.domain.model.Puerta;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/puerta")
public class PuertaController {
    private final CreateUseCase<Puerta, CreatePuertaCommand> createUseCase;
    private final UpdateUseCase<Puerta, CreatePuertaCommand,Long> updateUseCase;
    private final DeleteUseCase<Puerta,Long> deleteUseCase;
    private final GetUseCase<Long> getUseCase;
    private final ListUseCase listUseCase;

    public PuertaController(PuertaService service) {
        this.createUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
        this.getUseCase = service;
        this.listUseCase = service;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePuertaCommand command){
        Puerta puerta =  createUseCase.create(command);
        IResponse response = new Message<>(puerta.getId(), "CREATE_PUERTA");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody CreatePuertaCommand command){
        Puerta puerta =  updateUseCase.update(command, id);
        IResponse response = new Message<>(puerta.getId(), "UPDATE_PUERTA");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Puerta puerta = deleteUseCase.delete(id);
        IResponse response = new Message<>(puerta.getId(), "DELETE_PUERTA");
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
