package com.angelldca.siga.application.port.out.evento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CheckEventUniquePort {
    boolean existsByNameAndDateRange(String name, LocalDate fechaInicio, LocalDate fechaFin, Object excludeId);
}

