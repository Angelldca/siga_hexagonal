package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Acceso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class AccesoResponse implements IResponse{
    private Long id;
    private PuertaPersonaResponse puertaPersona;
    private ZonaEventoResponse zonaEvento;
    private MenuEventoResponse menuEvento;
    private String nombreEvento;
    private LocalDateTime fecha;

    public AccesoResponse(Acceso acceso) {
        this.id = acceso.getId();
        this.puertaPersona = new PuertaPersonaResponse(acceso.getPuertaPersona());
        this.zonaEvento = new ZonaEventoResponse(acceso.getZonaEvento());
        this.menuEvento = new MenuEventoResponse(acceso.getMenuEvento());
        this.nombreEvento = acceso.getNombreEvento();
        this.fecha = acceso.getFecha();
    }
}
