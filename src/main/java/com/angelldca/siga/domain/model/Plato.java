package com.angelldca.siga.domain.model;

import lombok.Data;

import java.math.BigDecimal;



@Data
public class Plato {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String medida;
    private boolean disponible;
}
