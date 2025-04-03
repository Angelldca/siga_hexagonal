package com.angelldca.siga.infrastructure.adapter.out.repository.command;


import com.angelldca.siga.infrastructure.adapter.out.persistence.business_module.BusinessModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessModuleWriteDataJPARepository extends JpaRepository<BusinessModuleEntity, UUID> {
}
