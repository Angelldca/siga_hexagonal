package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Dpersona;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.EStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PersonaResponse implements IResponse{
    private Long idciudadano;
    private String area;
    private String rolinstitucional;
    private String primernombre;
    private String segundonombre;
    private String primerapellido;
    private String segundoapellido;
    private String solapin;
    private String carnetidentidad;
    private String provincia;
    private String municipio;
    private String sexo;
    private Boolean residente;
    private LocalDateTime fechanacimiento;
    private String idexpediente;
    private String codigobarra;
    private EStatus estado;

    public PersonaResponse(Dpersona dpersona) {
        this.idciudadano = dpersona.getIdciudadano();
        this.area = dpersona.getArea();
        this.rolinstitucional = dpersona.getRolinstitucional();
        this.primernombre = dpersona.getPrimernombre();
        this.segundonombre = dpersona.getSegundonombre();
        this.primerapellido = dpersona.getPrimerapellido();
        this.segundoapellido = dpersona.getSegundoapellido();
        this.solapin = dpersona.getSolapin();
        this.carnetidentidad = dpersona.getCarnetidentidad();
        this.provincia = dpersona.getProvincia();
        this.municipio = dpersona.getMunicipio();
        this.sexo = dpersona.getSexo();
        this.residente = dpersona.getResidente();
        this.fechanacimiento = dpersona.getFechanacimiento();
        this.idexpediente = dpersona.getIdexpediente();
        this.codigobarra = dpersona.getCodigobarra();
        this.estado = dpersona.getEstado();
    }
}
