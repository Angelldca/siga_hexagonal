package com.angelldca.siga.application.port.in.command.plato;


import com.angelldca.siga.application.port.in.command.ICommand;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlatoCommand implements ICommand {
    private String nombre;
    private Double precio;
    private Boolean disponible;
    private String medida;
    private UUID businessId;

}