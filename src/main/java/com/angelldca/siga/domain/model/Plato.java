package com.angelldca.siga.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plato {
    private Long id;
    private String nombre;
    private double precio;
    private String medida;
    private Boolean disponible;
}
