package com.angelldca.siga.application.port.out.persona;

import com.angelldca.siga.domain.model.Dpersona;

import java.util.Optional;

public interface PersonaOptionalPort {
    Optional<Dpersona> getOptionalPort(Long id);
}
