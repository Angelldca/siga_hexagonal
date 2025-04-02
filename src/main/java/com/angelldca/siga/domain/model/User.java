package com.angelldca.siga.domain.model;

import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.EUserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
        private UUID id;
        private String username;
        private String email;
        private String password;
        private EUserType type;
        private List<UserPermissionBusiness> userPermissionBusinesses;
}
