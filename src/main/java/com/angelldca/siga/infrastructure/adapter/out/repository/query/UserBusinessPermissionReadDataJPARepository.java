package com.angelldca.siga.infrastructure.adapter.out.repository.query;


import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserBusinessPermissionReadDataJPARepository extends JpaRepository<UserPermissionBusinessEntity, UUID>,
        JpaSpecificationExecutor<UserPermissionBusinessEntity> {
    Page<UserPermissionBusinessEntity> findAll(Specification specification, Pageable pageable);

}