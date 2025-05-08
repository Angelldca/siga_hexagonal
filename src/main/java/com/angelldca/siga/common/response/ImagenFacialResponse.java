package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Dimagenfacial;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ImagenFacialResponse implements IResponse{
    private Long id;
    private PersonaResponse persona;
    private byte[] foto;
    private Boolean valida;

    public ImagenFacialResponse(Dimagenfacial dimagenfacial) {
        this.id = dimagenfacial.getId();
        this.persona = new PersonaResponse(dimagenfacial.getPersona());
        this.foto = dimagenfacial.getFoto();
        this.valida = dimagenfacial.getValida();
    }
}
