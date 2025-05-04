package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.zona.CreateZonaCommand;
import com.angelldca.siga.application.port.in.command.zona.UpdateZonaCommand;
import com.angelldca.siga.application.port.in.command.zonaEvento.BulkCreateZonaEventoUseCase;
import com.angelldca.siga.application.port.in.command.zonaEvento.BulkUpdateZonaEventoUseCase;
import com.angelldca.siga.application.port.in.command.zonaEvento.CreateZonaEventoCommand;
import com.angelldca.siga.application.port.in.command.zonaEvento.UpdateZonaEventoCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.service.ZonaEventoService;
import com.angelldca.siga.application.service.ZonaService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Zona;
import com.angelldca.siga.domain.model.ZonaEvento;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/api/zona-evento")
public class ZonaEventoController {
    private final BulkCreateZonaEventoUseCase createUseCase;
    private final BulkUpdateZonaEventoUseCase updateUseCase;
    private final DeleteUseCase<ZonaEvento,UUID> deleteUseCase;
    private final GetUseCase<UUID> getUseCase;
    private final ListUseCase listUseCase;

    public ZonaEventoController(ZonaEventoService service) {
        this.createUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
        this.getUseCase = service;
        this.listUseCase = service;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateZonaEventoCommand command){
        List<ZonaEvento> zonas =  createUseCase.create(command);
        IResponse response = new Message<>(zonas.get(0).getZona().getId(), "CREATE_ZONA_EVENTO");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody UpdateZonaEventoCommand command){
        List<ZonaEvento> zonas =  updateUseCase.update(command,id);
        IResponse response = new Message<>(zonas.get(0).getZona().getId(), "UPDATE_ZONA_EVENTO");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ZonaEvento zona = deleteUseCase.delete(id);
        IResponse response = new Message<>(zona.getId(), "DELETE_ZONA_EVENTO");
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
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
