package com.angelldca.siga.domain.model;


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
public class Evento {
    private Long id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean activo;
    private Boolean ilimitado;
    private Boolean isDelete = false;
    private EventoType type;
    private Empresa empresa;
}
