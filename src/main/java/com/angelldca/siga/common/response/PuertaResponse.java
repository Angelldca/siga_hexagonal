package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Puerta;
import com.angelldca.siga.domain.model.Zona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PuertaResponse implements IResponse{
    private Long id;
    private String nombre;
    private ZonaResponse zona;

    public PuertaResponse(Puerta puerta) {
        this.id = puerta.getId();
        this.nombre = puerta.getNombre();
        this.zona = new ZonaResponse(puerta.getZona());
    }
}
