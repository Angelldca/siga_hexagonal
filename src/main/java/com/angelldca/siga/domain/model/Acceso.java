package com.angelldca.siga.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Acceso {
    private Long id;
    private PuertaPersona puertaPersona;
    private ZonaEvento zonaEvento;
    private MenuEvento menuEvento;
    private String nombreEvento;
    private LocalDateTime fecha;
}
