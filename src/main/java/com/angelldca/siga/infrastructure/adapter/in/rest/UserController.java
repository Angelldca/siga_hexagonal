package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.menu.CreateMenuCommand;
import com.angelldca.siga.application.port.in.command.menu.UpdateMenuCommand;
import com.angelldca.siga.application.port.in.command.user.CreateUserCommand;
import com.angelldca.siga.application.port.in.command.user.UpdateUserCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.service.MenuService;
import com.angelldca.siga.application.service.UserService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final CreateUseCase<User, CreateUserCommand> createUseCase;
    private final UpdateUseCase<User, UpdateUserCommand,UUID> updateUseCase;
    private final DeleteUseCase<User,UUID> deleteUseCase;
    private final GetUseCase<UUID> getUseCase;
    private final ListUseCase listUseCase;

    public UserController(UserService service) {
        this.createUseCase = service;
        this.updateUseCase = service;
        this.deleteUseCase = service;
        this.getUseCase = service;
        this.listUseCase = service;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserCommand command){
        User user =  createUseCase.create(command);
        IResponse response = new Message<>(user.getId(), "CREATE_USER");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable UUID id, @RequestBody UpdateUserCommand command){
        User user =  updateUseCase.update(command, id);
        IResponse response = new Message<>(user.getId(), "UPDATE_USER");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        User user = deleteUseCase.delete(id);
        IResponse response = new Message<>(user.getId(), "DELETE_USER");
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
