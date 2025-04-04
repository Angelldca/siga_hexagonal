package com.angelldca.siga.infrastructure.adapter.out.persistence.acceso;


import com.angelldca.siga.infrastructure.adapter.out.persistence.Evento.EventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.empresa.EmpresaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menu.MenuEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.menuEvento.MenuEventoEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta.PuertaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona.PuertaPersonaEntity;
import com.angelldca.siga.infrastructure.adapter.out.persistence.zonaEvento.ZonaEventoEntity;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puerta_persona_id", nullable = false)
    private PuertaPersonaEntity puertaPersona;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zona_evento_id", nullable = false)
    private ZonaEventoEntity zonaEvento;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_evento_id")
    private MenuEventoEntity menuEvento;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
