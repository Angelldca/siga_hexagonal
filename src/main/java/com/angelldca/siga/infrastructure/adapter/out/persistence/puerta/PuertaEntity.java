package com.angelldca.siga.infrastructure.adapter.out.persistence.puerta;


import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zona.ZonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "puerta")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PuertaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zona_id")
    private ZonaEntity zona;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "puerta_id")
    private List<DpersonaEntity> personas;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
