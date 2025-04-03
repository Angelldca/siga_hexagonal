package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Empresa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class BusinessResponse implements IResponse{
    private UUID id;
    private String nombre;
    private String logo;
    private String address;

    public BusinessResponse(Empresa empresa) {
        this.id = empresa.getId();
        this.nombre = empresa.getNombre();
        this.logo = empresa.getLogo();
        this.address = empresa.getAddress();
    }
}
