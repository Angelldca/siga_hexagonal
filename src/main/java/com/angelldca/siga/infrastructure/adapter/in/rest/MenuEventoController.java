package com.angelldca.siga.infrastructure.adapter.in.rest;



import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;

import com.angelldca.siga.application.port.in.command.menu.UpdateMenuCommand;
import com.angelldca.siga.application.port.in.command.menuEvento.CreateMenuEventoCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.service.MenuEventoService;

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

import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/api/menu-evento")
public class MenuEventoController {
    private final UpdateUseCase<MenuEvento, CreateMenuEventoCommand, UUID> updateUseCase;
    private final DeleteUseCase<MenuEvento,UUID> deleteUseCase;
    private final GetUseCase<UUID> getUseCase;
    private final ListUseCase listUseCase;

    public MenuEventoController(MenuEventoService menuService) {
        this.updateUseCase = menuService;
        this.deleteUseCase = menuService;
        this.getUseCase = menuService;
        this.listUseCase = menuService;
    }
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?>  update(@PathVariable UUID id, @RequestBody CreateMenuEventoCommand command){
        MenuEvento menu =  updateUseCase.update(command, id);
        IResponse response = new Message<>(menu.getId(), "UPDATE_MENU_EVENTO");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        MenuEvento menu = deleteUseCase.delete(id);
        IResponse response = new Message<>(menu.getId(), "DELETE_MENU_EVENTO");
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
