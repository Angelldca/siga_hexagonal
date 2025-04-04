package com.angelldca.siga.application.port.in.command.menuEvento;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuEventoCommand {
    private Long eventoId;
    private Long menuId;
}
