package com.angelldca.siga.application.port.in.command.plato;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePlatoCommand {
    private String nombre;
    private BigDecimal precio;
    private Boolean disponible;
    private String medida;
}
