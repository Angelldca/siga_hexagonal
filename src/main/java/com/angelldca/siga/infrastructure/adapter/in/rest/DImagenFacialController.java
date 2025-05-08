package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.dimagen.CreateImagenCommand;
import com.angelldca.siga.application.port.in.command.evento.CreateEventoCommand;
import com.angelldca.siga.application.port.in.command.evento.UpdateEventCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.service.DImagenFacialService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.domain.model.Dimagenfacial;
import com.angelldca.siga.domain.model.Evento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/imagen-facial")
public class DImagenFacialController {
    private final CreateUseCase<Dimagenfacial, CreateImagenCommand> createUseCase;
    private final UpdateUseCase<Dimagenfacial, CreateImagenCommand,Long> updateUseCase;
    private final GetUseCase<Long> getUseCase;

    public DImagenFacialController(DImagenFacialService service) {
        this.createUseCase = service;
        this.updateUseCase = service;
        this.getUseCase = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateImagenCommand command){
        Dimagenfacial imagen =  createUseCase.create(command);
        IResponse response = new Message<>(imagen.getPersona().getId(), "CREATE_IMAGEN");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody CreateImagenCommand command){
        Dimagenfacial imagen =  updateUseCase.update(command, id);
        IResponse response = new Message<>(imagen.getPersona().getId(), "UPDATE_IMAGEN");
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        IResponse response = getUseCase.getById(id);
        return ResponseEntity.ok(response);
    }
}
