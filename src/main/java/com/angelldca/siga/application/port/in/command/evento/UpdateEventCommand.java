package com.angelldca.siga.application.port.in.command.evento;


import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventCommand {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean activo;
    private EventoType type;
    private Boolean ilimitado;
}
