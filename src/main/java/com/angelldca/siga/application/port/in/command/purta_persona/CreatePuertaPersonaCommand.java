package com.angelldca.siga.application.port.in.command.purta_persona;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePuertaPersonaCommand {
    private Long personaId;
    private Long puertaId;
}
