package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Module;
import com.angelldca.siga.domain.model.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PermissionResponse implements IResponse{
    private Long id;
    private String code;
    private String description;
    private String action;
    private Module module;

    public PermissionResponse(Permission permission) {
        this.id = permission.getId();
        this.code = permission.getCode();
        this.description = permission.getDescription();
        this.action = permission.getAction();
        this.module = permission.getModule();
    }
}
