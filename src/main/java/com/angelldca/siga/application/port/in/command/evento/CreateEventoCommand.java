package com.angelldca.siga.application.port.in.command.evento;


import com.angelldca.siga.application.port.in.command.ICommand;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventoCommand implements ICommand {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean activo;
    private Boolean ilimitado;
    private UUID empresa;
}
