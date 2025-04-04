package com.angelldca.siga.application.port.out.user_permission_business;

import com.angelldca.siga.domain.model.UserPermissionBusiness;

import java.util.List;
import java.util.UUID;

public interface GetAllByUserIdPort {
    List<UserPermissionBusiness> getAllByUserId(UUID userId);
}
