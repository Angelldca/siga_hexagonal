package com.angelldca.siga.infrastructure.adapter.out.repository.command;

import com.angelldca.siga.infrastructure.adapter.out.persistence.modulos.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ModuleWriteDataJPARepository extends JpaRepository<ModuleEntity, UUID> {
}
