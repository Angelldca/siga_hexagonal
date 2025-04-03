package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Evento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class EventoResponse implements IResponse{
    private Long id;
    private String nombre;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    //private Menu menu;
    private Boolean activo;
    private Boolean ilimitado;

    public EventoResponse(Evento evento) {
        this.id = evento.getId();
        this.nombre = evento.getNombre();
        this.fechaInicio = evento.getFechaInicio();
        this.fechaFin = evento.getFechaFin();
        this.activo = evento.getActivo();
        this.ilimitado = evento.getIlimitado();
    }
}
