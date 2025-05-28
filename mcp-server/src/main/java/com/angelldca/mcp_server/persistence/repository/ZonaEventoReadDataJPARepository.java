package com.angelldca.mcp_server.persistence.repository;

import com.angelldca.mcp_server.persistence.entities.ZonaEventoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ZonaEventoReadDataJPARepository extends JpaRepository<ZonaEventoEntity, UUID> {


}