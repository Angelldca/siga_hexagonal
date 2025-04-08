package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Evento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class EventoResponse implements IResponse{
    private Long id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    //private Menu menu;
    private Boolean activo;
    private Boolean ilimitado;

    public EventoResponse(Evento evento) {
        this.id = evento.getId();
        this.nombre = evento.getNombre();
        this.fechaInicio = evento.getFechaInicio();
        this.fechaFin = evento.getFechaFin();
        this.horaInicio = evento.getHoraInicio();
        this.horaFin = evento.getHoraFin();
        this.activo = evento.getActivo();
        this.ilimitado = evento.getIlimitado();
    }
}
