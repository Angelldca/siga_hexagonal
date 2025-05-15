package com.angelldca.siga.domain.model;

import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuEvento {
    private UUID id;
    private Menu menu;
    private Evento evento;
    private LocalDate fecha;
    private Boolean isDelete;
}
