package com.angelldca.siga.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Puerta {
    private Long id;
    private String nombre;
    private Zona zona;
    private Boolean isDelete;
}
