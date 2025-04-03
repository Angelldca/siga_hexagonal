package com.angelldca.siga.application.port.in.command.permission;


import com.angelldca.siga.domain.model.Module;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionCommand {
    private String description;
    private String action;
    private Long moduleId;
}
