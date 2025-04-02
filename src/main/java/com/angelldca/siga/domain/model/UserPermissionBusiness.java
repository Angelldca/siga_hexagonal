package com.angelldca.siga.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionBusiness {
    private UUID id;
    private Permission permission;
    private Empresa empresa;

}
