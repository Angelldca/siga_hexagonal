package com.angelldca.siga.application.port.out.evento;

import java.time.LocalDateTime;

public interface CheckEventUniquePort {
    boolean existsByNameAndDateRange(String name, LocalDateTime fechaInicio, LocalDateTime fechaFin, Object excludeId);
}

