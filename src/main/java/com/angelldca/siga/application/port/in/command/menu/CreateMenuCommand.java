package com.angelldca.siga.application.port.in.command.menu;


import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Plato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuCommand {
    private Boolean disponible;
    private Long eventoId;
    private List<Long> platosId;
}
