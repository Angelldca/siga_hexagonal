package com.angelldca.siga.infrastructure.adapter.out.repository.query;


import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaReadDataJPARepository extends JpaRepository<DpersonaEntity,Long>,
        JpaSpecificationExecutor<DpersonaEntity> {
    Page<DpersonaEntity> findAll(Specification specification, Pageable pageable);
}
