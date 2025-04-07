package com.angelldca.siga.application.port.in.command.zona;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateZonaCommand {
    private String nombre;
    private UUID empresaId;

}
