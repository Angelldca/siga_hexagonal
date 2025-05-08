package com.angelldca.siga.application.port.in.command.dimagen;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateImagenCommand{
    private Long personaId;
    private String  foto;
}
