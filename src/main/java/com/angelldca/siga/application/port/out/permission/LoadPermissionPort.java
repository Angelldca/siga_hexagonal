package com.angelldca.siga.application.port.out.permission;

import com.angelldca.siga.domain.model.Permission;

import java.util.List;

public interface LoadPermissionPort {
    List<Permission> loadAllByIds(List<Long> ids);
}
