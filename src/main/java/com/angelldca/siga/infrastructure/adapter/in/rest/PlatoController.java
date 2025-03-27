package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.plato.*;
import com.angelldca.siga.common.WebAdapter;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@WebAdapter
@RestController
@RequestMapping("/api/plato")
public class PlatoController {
    //CreatePlatoUseCase, UpdatePlatoUseCase, DeletePlatoUseCase

    private final CreatePlatoUseCase createPlatoUseCase;
    private final UpdatePlatoUseCase updatePlatoUseCase;
    private final DeletePlatoUseCase deletePlatoUseCase;

    public PlatoController(CreatePlatoUseCase createPlatoUseCase, UpdatePlatoUseCase updatePlatoUseCase, DeletePlatoUseCase deletePlatoUseCase) {
        this.createPlatoUseCase = createPlatoUseCase;
        this.updatePlatoUseCase = updatePlatoUseCase;
        this.deletePlatoUseCase = deletePlatoUseCase;
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
}
