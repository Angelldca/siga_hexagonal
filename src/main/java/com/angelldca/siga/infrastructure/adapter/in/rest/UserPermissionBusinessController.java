package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.module.CreateModuleCommand;
import com.angelldca.siga.application.port.in.command.module.UpdateModuleCommand;
import com.angelldca.siga.application.port.in.command.userPermissionBusiness.CreateUserPermissionBusinessCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.service.ModuleService;
import com.angelldca.siga.application.service.UserPermissionBusinessService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Module;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/api/user-permission-business")

public class UserPermissionBusinessController {
    private final CreateUseCase<UserPermissionBusiness, CreateUserPermissionBusinessCommand> createUseCase;
    private final UpdateUseCase<UserPermissionBusiness, CreateUserPermissionBusinessCommand, UUID> updateUseCase;
    private final DeleteUseCase<UserPermissionBusiness,UUID> deleteUseCase;
    private final GetUseCase<UUID> getUseCase;
    private final ListUseCase listUseCase;

    public UserPermissionBusinessController(UserPermissionBusinessService service) {
        this.createUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
        this.getUseCase = service;
        this.listUseCase = service;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserPermissionBusinessCommand command){
        UserPermissionBusiness domain =  createUseCase.create(command);
        IResponse response = new Message<>(domain.getId(), "CREATE_USER_PERMISSION_BUSINESS");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable UUID id, @RequestBody CreateUserPermissionBusinessCommand command){
        UserPermissionBusiness domain =  updateUseCase.update(command, id);
        IResponse response = new Message<>(domain.getId(), "UPDATE_USER_PERMISSION_BUSINESS");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        UserPermissionBusiness domain = deleteUseCase.delete(id);
        IResponse response = new Message<>(domain.getId(), "DELETE_USER_PERMISSION_BUSINESS");
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
