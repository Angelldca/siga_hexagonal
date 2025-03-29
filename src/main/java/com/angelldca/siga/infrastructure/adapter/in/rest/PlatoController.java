package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.plato.*;
import com.angelldca.siga.application.port.in.query.plato.GetPlatoUseCase;
import com.angelldca.siga.application.port.in.query.plato.ListPlatoUseCase;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.common.criteria.SearchRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/plato")
public class PlatoController {
    //CreatePlatoUseCase, UpdatePlatoUseCase, DeletePlatoUseCase

    private final CreatePlatoUseCase createPlatoUseCase;
    private final UpdatePlatoUseCase updatePlatoUseCase;
    private final DeletePlatoUseCase deletePlatoUseCase;
    private final GetPlatoUseCase getPlatoUseCase;
    private final ListPlatoUseCase listPlatoUseCase;

    public PlatoController(CreatePlatoUseCase createPlatoUseCase, UpdatePlatoUseCase updatePlatoUseCase, DeletePlatoUseCase deletePlatoUseCase, GetPlatoUseCase getPlatoUseCase, ListPlatoUseCase listPlatoUseCase) {
        this.createPlatoUseCase = createPlatoUseCase;
        this.updatePlatoUseCase = updatePlatoUseCase;
        this.deletePlatoUseCase = deletePlatoUseCase;
        this.getPlatoUseCase = getPlatoUseCase;
        this.listPlatoUseCase = listPlatoUseCase;
    }

    @PostMapping
    public void create(@RequestBody CreatePlatoCommand command){
        createPlatoUseCase.create(command);
    }
    @PatchMapping
    public void update(@RequestBody UpdatePlatoCommand command){
        updatePlatoUseCase.update(command);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id){
        deletePlatoUseCase.delete(id);
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
