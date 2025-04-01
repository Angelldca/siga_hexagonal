package com.angelldca.siga.infrastructure.adapter.out.persistence.identificacion;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.ntiposolapin.Ntiposolapin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "didentificacion")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Didentificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_identificacion")
    private Long idIdentificacion;

    @Column(name = "codigobarra", length = 10, nullable = false)
    private String codigobarra;

    @Column(name = "numerosolapin", length = 7, unique = true, nullable = false)
    private String numerosolapin;

    @Column(name = "serial", length = 10)
    private String serial;

    @Column(name = "estado", length = 1)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idtiposolapin")
    private Ntiposolapin idtiposolapin;

    @OneToOne
    @JoinColumn(name = "idciudadano")
    private DpersonaEntity dpersona;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
