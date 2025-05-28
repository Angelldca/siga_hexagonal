package com.angelldca.siga.common.response;


import com.angelldca.siga.domain.model.Empresa;
import com.angelldca.siga.domain.model.Manual;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ManualResponse implements IResponse{
    private Long id;
    private String nombre;
    private String descripcion;
    private String filePath;
   // private Empresa empresa;


    public ManualResponse(Manual manual) {
        this.id = manual.getId();
        this.nombre = manual.getNombre();
        this.descripcion = manual.getDescripcion();
        this.filePath = manual.getFilePath();
    }
}
