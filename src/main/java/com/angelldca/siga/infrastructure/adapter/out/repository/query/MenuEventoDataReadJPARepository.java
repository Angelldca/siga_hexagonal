package com.angelldca.siga.infrastructure.adapter.out.repository.query;


import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menuEvento.MenuEventoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuEventoDataReadJPARepository extends JpaRepository<MenuEventoEntity, UUID>,
        JpaSpecificationExecutor<MenuEventoEntity> {
    Page<MenuEventoEntity> findAll(Specification specification, Pageable pageable);


    //@Query("SELECT me FROM MenuEventoEntity me WHERE me.menu.id = :id")
    Optional<MenuEventoEntity> findFirstByMenu_Id(Long id);
}
