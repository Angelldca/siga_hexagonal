package com.angelldca.siga.common.response;



import com.angelldca.siga.domain.model.Menu;
;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MenuResponse implements IResponse{
    private Long id;
    private Double totalPrecio;
    private Boolean disponible;

    private List<PlatoResponse> platos;

    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.totalPrecio = menu.getTotalPrecio();
        this.disponible = menu.getDisponible();
        this.platos = menu.getPlatos().stream().map(PlatoResponse::new).toList();
    }
}
