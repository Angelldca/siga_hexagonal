package com.angelldca.siga.infrastructure.adapter.out.persistence.dimagenfacial;

import com.angelldca.siga.infrastructure.adapter.out.persistence.dpersona.DpersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Types;
import java.time.LocalDateTime;

@Entity
@Table(name = "dimagenfacial")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DimagenfacialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idciudadano", referencedColumnName = "idciudadano")
    private DpersonaEntity persona;

    @Lob
    @JdbcTypeCode(Types.BINARY)
    @Column(name = "foto", columnDefinition = "BYTEA")
    private byte[] foto;

    @Column(name = "valida")
    private Boolean valida;

    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime updatedAt;

}