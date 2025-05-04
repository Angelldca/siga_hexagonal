package com.angelldca.siga.application.port.in.command.zonaEvento;


import com.angelldca.siga.application.port.in.command.zona.CreateZonaCommand;
import com.angelldca.siga.domain.model.Zona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateZonaEventoCommand {
    private String nombre;
    private List<Long> eventosId;
}
