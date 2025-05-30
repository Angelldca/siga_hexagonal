package com.angelldca.siga.infrastructure.adapter.out.persistence.menu;


import com.angelldca.siga.application.port.out.DeleteListPort;
import com.angelldca.siga.application.port.out.Menu.MenuCRUDPort;

import com.angelldca.siga.common.anotations.PersistenceAdapter;
import com.angelldca.siga.common.exception.BusinessExceptionFactory;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.Plato;
import com.angelldca.siga.infrastructure.adapter.out.repository.command.MenuWriteDataJPARepository;
import com.angelldca.siga.infrastructure.adapter.out.repository.query.MenuReadDataJPARepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@PersistenceAdapter
@Qualifier("menuPersistenceAdapter")
public class MenuPersistenceAdapter implements MenuCRUDPort, DeleteListPort<Long> {
    private final MenuReadDataJPARepository query;
    private final MenuWriteDataJPARepository command;


    public MenuPersistenceAdapter(MenuReadDataJPARepository query, MenuWriteDataJPARepository command) {
        this.query = query;
        this.command = command;

    }

    @Override
    public Menu delete(Long id) {
        Menu domain = obtenerPorId(id);
        MenuEntity entity = MenuMapper.domainToEntity(domain);
        this.command.delete(entity);
        return domain;
    }

    @Override
    public Menu obtenerPorId(Long id) {
        Menu entity = this.query.findById(id)
                .map(MenuMapper::entityToDomain)
                .orElseThrow(() -> BusinessExceptionFactory.objectNotFound("id","Menu"));
        return entity;
    }

    @Override
    public Page<MenuEntity> list(Specification specifications, Pageable pageable) {
        return this.query.findAll(specifications,pageable);
    }

    @Override
    public Menu save(Menu menu) {
        MenuEntity entity = this.command.save(MenuMapper.domainToEntity(menu));
        return MenuMapper.entityToDomain(entity);
    }

    @Override
    public void deleteList(List<Long> id) {
        List<Menu> menus = id.stream()
                .map(this::obtenerPorId)
                .toList();

        menus.forEach(p -> {
            p.setIsDelete(true);
            this.save(p);
        });
    }
}
