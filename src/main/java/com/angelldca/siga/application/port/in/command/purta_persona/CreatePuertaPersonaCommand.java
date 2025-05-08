package com.angelldca.siga.application.port.in.command.purta_persona;


import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePuertaPersonaCommand {
    private Long personaId;
    private List<Long> puertaIds;
}
