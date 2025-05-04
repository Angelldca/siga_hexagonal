package com.angelldca.siga.application.port.in.command.zonaEvento;


import com.angelldca.siga.application.port.in.command.zona.CreateZonaCommand;
import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Zona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateZonaEventoCommand {
    private String nombre;
    private UUID empresa;
    private List<Long> eventosId;
}
