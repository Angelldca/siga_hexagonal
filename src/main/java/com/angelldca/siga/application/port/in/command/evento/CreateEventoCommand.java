package com.angelldca.siga.application.port.in.command.evento;


import com.angelldca.siga.application.port.in.command.ICommand;
import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventoCommand implements ICommand {
    private String nombre;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Boolean activo;
    private Boolean ilimitado;
    private UUID empresa;
}
