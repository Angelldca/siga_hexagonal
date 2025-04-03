package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.permission.CreatePermissionCommand;
import com.angelldca.siga.application.port.in.command.permission.UpdatePermissionCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;

import com.angelldca.siga.application.service.PermissionService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;

import com.angelldca.siga.domain.model.Permission;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/permission")
public class PermissionCntroller {
    private final CreateUseCase<Permission, CreatePermissionCommand> createUseCase;
    private final UpdateUseCase<Permission, UpdatePermissionCommand, Long> updateUseCase;
    private final DeleteUseCase<Permission,Long> deleteUseCase;
    private final GetUseCase<Long> getUseCase;
    private final ListUseCase listUseCase;

    public PermissionCntroller(PermissionService service) {
        this.createUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
        this.getUseCase = service;
        this.listUseCase = service;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePermissionCommand command){
        Permission domain =  createUseCase.create(command);
        IResponse response = new Message<>(domain.getId(), "CREATE_PERMISSION");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody UpdatePermissionCommand command){
        Permission domain =  updateUseCase.update(command, id);
        IResponse response = new Message<>(domain.getId(), "UPDATE_PERMISSION");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Permission domain = deleteUseCase.delete(id);
        IResponse response = new Message<>(domain.getId(), "DELETE_PERMISSION");
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
