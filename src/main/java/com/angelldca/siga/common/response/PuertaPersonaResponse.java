package com.angelldca.siga.common.response;



import com.angelldca.siga.domain.model.PuertaPersona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PuertaPersonaResponse implements IResponse{
    private UUID id;
    private PersonaResponse persona;
    private PuertaResponse puerta;

    public PuertaPersonaResponse(PuertaPersona puertaPersona) {
        this.id = puertaPersona.getId();
        this.persona = new PersonaResponse(puertaPersona.getPersona());
        this.puerta = new PuertaResponse(puertaPersona.getPuerta());
    }
}
