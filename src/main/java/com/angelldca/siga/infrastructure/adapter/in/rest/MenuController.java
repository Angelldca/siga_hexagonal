package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteListUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.menu.CreateMenuCommand;
import com.angelldca.siga.application.port.in.command.menu.UpdateMenuCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.port.out.DeleteListCommand;
import com.angelldca.siga.application.service.MenuService;
import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.MenuEvento;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final CreateUseCase<MenuEvento, CreateMenuCommand> createUseCase;
    private final UpdateUseCase<Menu, UpdateMenuCommand,Long> updateUseCase;
    private final DeleteUseCase<Menu,Long> deleteUseCase;
    private final GetUseCase<Long> getUseCase;
    private final ListUseCase listUseCase;
    private final DeleteListUseCase<Long> deleteListUseCase;
    public MenuController(MenuService menuService ) {
        this.createUseCase = menuService;
        this.updateUseCase = menuService;
        this.deleteUseCase = menuService;
        this.getUseCase = menuService;
        this.listUseCase = menuService;
        this.deleteListUseCase = menuService;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateMenuCommand command){
        MenuEvento menu =  createUseCase.create(command);
        IResponse response = new Message<>(menu.getId(), "CREATE_MENU");
        return ResponseEntity.ok(response);
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody UpdateMenuCommand command){
        Menu menu =  updateUseCase.update(command, id);
        IResponse response = new Message<>(menu.getId(), "UPDATE_MENU");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Menu menu = deleteUseCase.delete(id);
        IResponse response = new Message<>(menu.getId(), "DELETE_MENU");
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
    @DeleteMapping(path = "/delete-list")
    public ResponseEntity<?> deleteAll(@RequestBody DeleteListCommand<Long> command){
        deleteListUseCase.deleteList(command);
        IResponse response = new Message<>(null, "DELETE_LIST");
        return ResponseEntity.ok(response);
    }
}
