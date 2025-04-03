package com.angelldca.siga.application.port.in.command.userPermissionBusiness;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserPermissionBusinessCommand {
    private UUID userId;
    private Long permissionId;
    private UUID businessId;
}
