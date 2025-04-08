package com.angelldca.siga.application.port.in.command.Puerta;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePuertaCommand {
    private String nombre;
    private Long zonaId;
}
