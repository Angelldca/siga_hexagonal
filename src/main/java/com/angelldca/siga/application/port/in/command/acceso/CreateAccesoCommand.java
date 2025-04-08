package com.angelldca.siga.application.port.in.command.acceso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccesoCommand {
    private UUID puertaPersonaId;
    private UUID zonaEventoId;
    private UUID menuEventoId;
}
