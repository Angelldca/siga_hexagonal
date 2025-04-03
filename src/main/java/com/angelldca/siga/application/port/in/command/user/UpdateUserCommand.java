package com.angelldca.siga.application.port.in.command.user;

import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.EUserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateUserCommand {
    private String username;
    private String email;
    private String password;
    private String image;
    private EUserType type;
    private List<UUID> userPermissionBusinesses;
}
