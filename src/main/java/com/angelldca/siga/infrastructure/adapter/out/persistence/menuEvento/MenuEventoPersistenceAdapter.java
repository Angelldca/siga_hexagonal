package com.angelldca.siga.infrastructure.adapter.out.persistence.menuEvento;


import com.angelldca.siga.application.port.out.menuEvento.MenuEventoCRUDPort;
import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.MenuEvento;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuMapper;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.MenuEventoDataWriteJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.MenuEventoDataReadJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@PersistenceAdapter
@Qualifier("menuEventoPersistenceAdapter")
public class MenuEventoPersistenceAdapter implements MenuEventoCRUDPort {
    private final MenuEventoDataReadJPARepository query;
    private final MenuEventoDataWriteJPARepository command;

    public MenuEventoPersistenceAdapter(MenuEventoDataReadJPARepository query, MenuEventoDataWriteJPARepository command) {
        this.query = query;
        this.command = command;
    }

    @Override
    public MenuEvento delete(UUID id) {
        MenuEvento menu = obtenerPorId(id);
        MenuEventoEntity entity = MenuEventoMapper.domainToEntity(menu);
        this.command.delete(entity);
        return menu;
    }

    @Override
    public MenuEvento obtenerPorId(UUID id) {
        MenuEvento entity = this.query.findById(id)
                .map(MenuEventoMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","MenuEvento"));
        return entity;
    }

    @Override
    public Page<MenuEventoEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public MenuEvento save(MenuEvento domain) {
        MenuEventoEntity entity = this.command.save(MenuEventoMapper.domainToEntity(domain));
        return MenuEventoMapper.entityToDomain(entity);
    }
}
