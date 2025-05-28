package com.angelldca.mcp_server.persistence.repository;

import com.angelldca.mcp_server.persistence.entities.ZonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaReadDataJPARepository extends JpaRepository<ZonaEntity, Long>{

}