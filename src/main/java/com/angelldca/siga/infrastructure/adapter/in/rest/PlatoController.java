package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.plato.*;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.domain.model.Plato;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/plato")
public class PlatoController {
    //CreatePlatoUseCase, UpdatePlatoUseCase, DeletePlatoUseCase

    private final CreateUseCase<Plato,CreatePlatoCommand> createPlatoUseCase;
    private final UpdateUseCase<Plato,UpdatePlatoCommand,Long> updatePlatoUseCase;
    private final DeleteUseCase<Plato,Long> deletePlatoUseCase;
    private final GetUseCase<Long> getPlatoUseCase;
    private final ListUseCase listPlatoUseCase;


    public PlatoController(CreateUseCase createPlatoUseCase, UpdateUseCase updatePlatoUseCase, DeleteUseCase deletePlatoUseCase, GetUseCase getPlatoUseCase, ListUseCase listPlatoUseCase) {
        this.createPlatoUseCase = createPlatoUseCase;
        this.updatePlatoUseCase = updatePlatoUseCase;
        this.deletePlatoUseCase = deletePlatoUseCase;
        this.getPlatoUseCase = getPlatoUseCase;
        this.listPlatoUseCase = listPlatoUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePlatoCommand command){
       Plato plato =  createPlatoUseCase.create(command);
        IResponse response = new Message<>(plato.getId(), "CREATE_PLATO");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody UpdatePlatoCommand command){
        Plato plato =  updatePlatoUseCase.update(command, id);
        IResponse response = new Message<>(plato.getId(), "UPDATE_PLATO");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Plato plato = deletePlatoUseCase.delete(id);
        IResponse response = new Message<>(plato.getId(), "DELETE_PLATO");
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        IResponse response = getPlatoUseCase.getPlatoById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageableUtil.createPageable(request);
        PaginatedResponse response = this.listPlatoUseCase.search(pageable, request.getFilter());
        return ResponseEntity.ok(response);
    }
}
