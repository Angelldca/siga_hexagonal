package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Module;
import com.angelldca.siga.domain.model.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ModuleResponse implements IResponse{
    private Long id;
    private String name;
    private String image;
    private String description;
    private List<Permission> permissions;

    public ModuleResponse(Module module) {
        this.id = module.getId();
        this.name = module.getName();
        this.image = module.getImage();
        this.description = module.getDescription();
        this.permissions = module.getPermissions();
    }
}
