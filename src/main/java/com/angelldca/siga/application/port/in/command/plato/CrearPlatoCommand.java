package com.angelldca.siga.application.port.in.command.plato;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearPlatoCommand {
    private String nombre;
    private BigDecimal precio;
    private boolean disponible;
    private String medida;

}