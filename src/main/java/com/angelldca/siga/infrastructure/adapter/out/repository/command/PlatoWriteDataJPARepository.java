package com.angelldca.siga.infrastructure.adapter.out.repository.command;

import com.angelldca.siga.infrastructure.adapter.out.persistence.plato.PlatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlatoWriteDataJPARepository extends JpaRepository<PlatoEntity,Long> {
}
