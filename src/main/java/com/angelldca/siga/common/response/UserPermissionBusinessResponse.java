package com.angelldca.siga.common.response;

import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Permission;
import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
public class UserPermissionBusinessResponse implements IResponse{
    private UUID id;
    private Permission permission;
    private UserResponse user;
    private Empresa empresa;

    public UserPermissionBusinessResponse(UserPermissionBusiness userPermissionBusiness) {
        this.id = userPermissionBusiness.getId();
        this.permission = userPermissionBusiness.getPermission();
        this.user = new UserResponse(userPermissionBusiness.getUser());
        this.empresa = userPermissionBusiness.getEmpresa();
    }
}
