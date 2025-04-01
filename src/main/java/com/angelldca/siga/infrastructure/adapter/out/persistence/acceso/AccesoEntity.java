package com.angelldca.siga.infrastructure.adapter.out.persistence.acceso;


import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "acceso")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccesoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "acceso_persona",
            joinColumns = @JoinColumn(name = "acceso_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )
    private List<DpersonaEntity> personas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puerta_id", nullable = false) //fk tabla puerta
    private PuertaEntity puerta;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
