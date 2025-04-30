package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Zona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ZonaResponse implements IResponse{
    private Long id;
    private String nombre;

    public ZonaResponse(Zona domain) {
        this.id = domain.getId();
        this.nombre = domain.getNombre();

    }
}
