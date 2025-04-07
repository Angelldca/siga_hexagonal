package com.angelldca.siga.infrastructure.adapter.out.repository.command;


import com.angelldca.siga.infrastructure.adapter.out.persistence.zona.ZonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaWriteDataJPARepository extends JpaRepository<ZonaEntity, Long> {
}
