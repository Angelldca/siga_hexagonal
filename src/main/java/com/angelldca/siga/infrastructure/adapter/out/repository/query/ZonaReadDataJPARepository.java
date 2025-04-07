package com.angelldca.siga.infrastructure.adapter.out.repository.query;


import com.angelldca.siga.infrastructure.adapter.out.persistence.zona.ZonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ZonaReadDataJPARepository extends JpaRepository<ZonaEntity, Long>,
        JpaSpecificationExecutor<ZonaEntity> {
    Page<ZonaEntity> findAll(Specification specification, Pageable pageable);

}