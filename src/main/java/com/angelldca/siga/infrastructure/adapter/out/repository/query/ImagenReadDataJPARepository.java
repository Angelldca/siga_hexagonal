package com.angelldca.siga.infrastructure.adapter.out.repository.query;

import com.angelldca.siga.infrastructure.adapter.out.persistence.dimagenfacial.DimagenfacialEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImagenReadDataJPARepository extends JpaRepository<DimagenfacialEntity,Long> {

    Optional<DimagenfacialEntity> findByPersona_Id(Long id);
    //boolean existsByPersona_Id(Long id);
    //void deleteByPersona_Id(Long id);
}
