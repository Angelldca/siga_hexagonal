package com.angelldca.siga.application.port.out.user_permission_business;

import com.angelldca.siga.application.port.out.DeletePort;
import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.ListPort;
import com.angelldca.siga.application.port.out.SavePort;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.user_permission_business.UserPermissionBusinessEntity;

import java.util.UUID;

public interface UserPermissionBusinessCRUDPort extends DeletePort<UserPermissionBusiness, UUID>, GetPort<UserPermissionBusiness,UUID>,
        ListPort<UserPermissionBusinessEntity>, SavePort<UserPermissionBusiness> {
}
