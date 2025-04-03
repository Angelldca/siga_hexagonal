package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.CreateUseCase;
import com.angelldca.siga.application.port.in.command.DeleteUseCase;
import com.angelldca.siga.application.port.in.command.UpdateUseCase;
import com.angelldca.siga.application.port.in.command.businessModule.CreateBusinessModuleCommand;
import com.angelldca.siga.application.port.in.command.businessModule.UpdateBusinessModuleCommand;
import com.angelldca.siga.application.port.in.query.GetUseCase;
import com.angelldca.siga.application.port.in.query.ListUseCase;
import com.angelldca.siga.application.service.BusinessModuleService;

import com.angelldca.siga.common.anotations.WebAdapter;
import com.angelldca.siga.common.criteria.PageableUtil;
import com.angelldca.siga.common.criteria.SearchRequest;
import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.common.response.Message;
import com.angelldca.siga.common.response.PaginatedResponse;
import com.angelldca.siga.domain.model.BusinessModule;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/api/business-module")
public class BusinessModuleController {
        private final CreateUseCase<BusinessModule, CreateBusinessModuleCommand> createUseCase;
        private final UpdateUseCase<BusinessModule, UpdateBusinessModuleCommand, UUID> updateUseCase;
        private final DeleteUseCase<BusinessModule,UUID> deleteUseCase;
        private final GetUseCase<UUID> getUseCase;
        private final ListUseCase listUseCase;

        public BusinessModuleController(BusinessModuleService service) {
            this.createUseCase = service;
            this.updateUseCase = service;
            this.deleteUseCase = service;
            this.getUseCase = service;
            this.listUseCase = service;
        }
        @PostMapping
        public ResponseEntity<?> create(@RequestBody CreateBusinessModuleCommand command){
            BusinessModule domain =  createUseCase.create(command);
            IResponse response = new Message<>(domain.getId(), "CREATE_BUSINESS_MODULE");
            return ResponseEntity.ok(response);
        }
        @PatchMapping(path = "/{id}")
        public ResponseEntity<?>  update(@PathVariable UUID id, @RequestBody UpdateBusinessModuleCommand command){
            BusinessModule domain =  updateUseCase.update(command, id);
            IResponse response = new Message<>(domain.getId(), "UPDATE_BUSINESS_MODULE");
            return ResponseEntity.ok(response);
        }

        @DeleteMapping(path = "/{id}")
        public ResponseEntity<?> delete(@PathVariable UUID id){
            BusinessModule domain = deleteUseCase.delete(id);
            IResponse response = new Message<>(domain.getId(), "DELETE_BUSINESS_MODULE");
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
