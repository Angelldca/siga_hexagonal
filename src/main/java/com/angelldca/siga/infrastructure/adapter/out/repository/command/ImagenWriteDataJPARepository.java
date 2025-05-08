package com.angelldca.siga.infrastructure.adapter.out.repository.command;

import com.angelldca.siga.infrastructure.adapter.out.persistence.dimagenfacial.DimagenfacialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImagenWriteDataJPARepository extends JpaRepository<DimagenfacialEntity, Long> {

}