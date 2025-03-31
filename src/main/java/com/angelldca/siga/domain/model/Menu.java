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
    private double totalPrecio;
    private Boolean disponible;
    private Evento evento;
    private List<Plato> platos;
}
