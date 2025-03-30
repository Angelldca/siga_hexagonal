package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Menu;
import com.angelldca.siga.domain.model.Plato;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MenuResponse implements IResponse{
    private Long id;
    private BigDecimal totalPrecio;
    private Boolean disponible;
    private EventoResponse evento;
    private List<PlatoResponse> platos;

    public MenuResponse(Menu menu) {
        this.id = menu.getId();
        this.totalPrecio = menu.getTotalPrecio();
        this.disponible = menu.getDisponible();
        this.evento = new EventoResponse(menu.getEvento());
        this.platos = menu.getPlatos().stream().map(PlatoResponse::new).toList();
    }
}
