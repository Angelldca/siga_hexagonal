package com.angelldca.siga.infrastructure.adapter.out.repository.query;


import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserReadDataJPARepository extends JpaRepository<UserEntity, UUID>,
        JpaSpecificationExecutor<UserEntity> {
    Page<UserEntity> findAll(Specification specification, Pageable pageable);
    Optional<UserEntity> findByEmail(String email);

}
