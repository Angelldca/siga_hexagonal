package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Acceso;
import com.angelldca.siga.domain.model.Dpersona;
import com.angelldca.siga.domain.model.Puerta;
import com.angelldca.siga.domain.model.PuertaPersona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class AccesoResponse implements IResponse{
    private Long id;
    private PuertaResponse puerta;
    private PersonaResponse persona;
    private ZonaEventoResponse zonaEvento;
    private MenuEventoResponse menuEvento;
    private String nombreEvento;
    private LocalDateTime fecha;

    public AccesoResponse(Acceso acceso) {
        this.id = acceso.getId();
        this.puerta = new PuertaResponse(acceso.getPuerta());
        this.persona = new PersonaResponse(acceso.getPersona());
        this.zonaEvento = new ZonaEventoResponse(acceso.getZonaEvento());
        this.menuEvento = new MenuEventoResponse(acceso.getMenuEvento());
        this.nombreEvento = acceso.getNombreEvento();
        this.fecha = acceso.getFecha();
    }
}
