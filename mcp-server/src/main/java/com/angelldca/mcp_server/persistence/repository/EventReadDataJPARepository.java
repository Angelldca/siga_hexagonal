package com.angelldca.mcp_server.persistence.repository;

import com.angelldca.mcp_server.persistence.entities.EventoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EventReadDataJPARepository extends JpaRepository<EventoEntity,Long>{


    boolean existsByNombreAndFechaInicioAndFechaFinAndIdNot(
            String nombre,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Long id
    );
}
