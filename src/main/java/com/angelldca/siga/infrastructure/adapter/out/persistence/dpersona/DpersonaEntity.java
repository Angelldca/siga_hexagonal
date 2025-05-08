package com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona;

import com.angelldca.siga.infrastructure.adapter.out.persistence.dimagenfacial.DimagenfacialEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "dpersona")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DpersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idciudadano")
    private Long id;

    @Column(name = "area", length = 150)
    private String area;

    @Column(name = "rol_institucional", length = 100)
    private String rolinstitucional;

    @Column(name = "nombre")
    private String nombre;


    @Column(name = "solapin", length = 10)
    private String solapin;

    @Column(name = "carnetidentidad", length = 35)
    private String carnetidentidad;

    @Column(name = "provincia", length = 100)
    private String provincia;

    @Column(name = "municipio", length = 100)
    private String municipio;

    @Column(name = "sexo", length = 35)
    private String sexo;

    @Column(name = "residente")
    private Boolean residente;

    @Column(name = "fechanacimiento")
    private LocalDateTime fechanacimiento;

    @Column(name = "idexpediente", length = 20)
    private String idexpediente;

    @Column(name = "codigobarra", length = 10, nullable = false)
    private String codigobarra;


    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EStatus estado;

    private Boolean isDelete = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;




    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime updatedAt;

}
