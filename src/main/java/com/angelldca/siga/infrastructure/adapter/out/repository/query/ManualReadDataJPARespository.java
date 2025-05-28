package com.angelldca.siga.infrastructure.adapter.out.repository.query;

import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.manual.ManualEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ManualReadDataJPARespository extends JpaRepository<ManualEntity,Long>,
        JpaSpecificationExecutor<ManualEntity> {
    Page<ManualEntity> findAll(Specification specification, Pageable pageable);
    Optional<ManualEntity> findByNombre(String nombre);
    List<ManualEntity> findByNombreContainingIgnoreCase(String nombreParcial);
    @Query("SELECT m.nombre FROM ManualEntity m")
    List<String> findAllNombres();
}
