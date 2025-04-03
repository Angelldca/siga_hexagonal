package com.angelldca.siga.infrastructure.adapter.out.repository.command;

import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserBusinessPermissionWriteDataJPARepository extends JpaRepository<UserPermissionBusinessEntity, UUID> {
}
