package com.angelldca.siga.infrastructure.adapter.out.repository.command;



import com.angelldca.siga.infrastructure.adapter.out.persistence.puerta_persona.PuertaPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PuertaPersonaWriteDataJPARepository extends JpaRepository<PuertaPersonaEntity, UUID> {
}
