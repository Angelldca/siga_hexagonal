package com.angelldca.siga.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dimagenfacial {
    private Long id;
    private Dpersona persona;
    private byte[] foto;
    private Boolean valida;
}
