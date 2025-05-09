package com.angelldca.siga.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Menu {
    private Long id;
    private Double totalPrecio;
    private Boolean disponible;
    private Boolean isDelete;
    private List<Plato> platos;
}
