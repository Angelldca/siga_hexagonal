package com.angelldca.siga.application.port.in.command.plato;


import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlatoCommand {
    private String nombre;
    private BigDecimal precio;
    private Boolean disponible;
    private String medida;

}