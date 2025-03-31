package com.angelldca.siga.common.response;


import com.angelldca.siga.common.response.IResponse;
import com.angelldca.siga.domain.model.Plato;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class PlatoResponse implements IResponse {
    private Long id;
    private String nombre;
    private Double precio;
    private String medida;
    private Boolean disponible;

    public PlatoResponse(Plato plato) {
        this.id = plato.getId();
        this.nombre = plato.getNombre();
        this.precio = plato.getPrecio();
        this.medida = plato.getMedida();
        this.disponible = plato.getDisponible();
    }
}
