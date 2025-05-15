package com.angelldca.siga.application.port.in.command.menu;


import com.angelldca.siga.domain.model.Evento;
import com.angelldca.siga.domain.model.Plato;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuCommand {
    private Boolean disponible;
    private Long eventoId;
    private List<Long> platosId;
    private LocalDate fecha;
}
