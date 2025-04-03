package com.angelldca.siga.application.port.in.command.user;

import com.angelldca.siga.application.port.in.command.ICommand;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.EUserType;
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
public class CreateUserCommand implements ICommand {
    private String username;
    private String email;
    private String password;
    private String image;
    private EUserType type;
    private List<UUID> userPermissionBusinesses;
}
