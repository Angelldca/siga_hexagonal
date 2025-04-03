package com.angelldca.siga.infrastructure.adapter.out.repository.query;

import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.UUID;


@Repository
public interface BusinessReadDataJPARepository extends JpaRepository<EmpresaEntity, UUID>,
        JpaSpecificationExecutor<EmpresaEntity> {
    Page<EmpresaEntity> findAll(Specification specification, Pageable pageable);

}