package com.angelldca.siga.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ZonaEvento {
    private UUID id;
    private Zona zona;
    private Evento evento;
}
