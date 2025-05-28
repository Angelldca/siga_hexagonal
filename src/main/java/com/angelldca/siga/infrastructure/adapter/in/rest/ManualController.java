package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.evento.CreateEventoCommand;
import com.angelldca.siga.application.port.in.command.evento.DeleteEventListUseCase;
import com.angelldca.siga.application.port.in.command.evento.UpdateEventCommand;
import com.angelldca.siga.application.port.in.command.manual.ManualCreateCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.service.ManualService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Manual;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@WebAdapter
@RestController
@RequestMapping("/api/manual")
public class ManualController {
    private final ManualService service;

    public ManualController(ManualService service) {
        this.service = service;

    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestBody ManualCreateCommand command, @RequestPart("file") MultipartFile file){
        Manual evento =  service.create(command,file);
        IResponse response = new Message<>(evento.getId(), "CREATE_MANUAL");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody ManualCreateCommand command){
        Manual evento =  service.update(command, id);
        IResponse response = new Message<>(evento.getId(), "UPDATE_MANUAL");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Manual evento = service.delete(id);
        IResponse response = new Message<>(evento.getId(), "DELETE_MANUAL");
        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        IResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody SearchRequest request) {
        Pageable pageable = PageableUtil.createPageable(request);
        PaginatedResponse response = this.service.search(pageable, request.getFilter());
        return ResponseEntity.ok(response);
    }

}
