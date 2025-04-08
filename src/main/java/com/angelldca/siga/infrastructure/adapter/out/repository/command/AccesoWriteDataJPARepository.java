package com.angelldca.siga.infrastructure.adapter.out.repository.command;


import com.angelldca.siga.infrastructure.adapter.out.persistence.acceso.AccesoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccesoWriteDataJPARepository extends JpaRepository<AccesoEntity, Long> {
}

