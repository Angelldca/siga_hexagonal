package com.angelldca.siga.domain.rule.evento;

import com.angelldca.siga.common.exception.ErrorField;
import com.angelldca.siga.domain.rule.BrokenRule;
import com.angelldca.siga.domain.rule.DomainErrorMessage;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventRangeDateValid extends BrokenRule {
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;

    public EventRangeDateValid(LocalDate fechaInicio, LocalDate fechaFin) {
        super(
                DomainErrorMessage.OBJECT_INVALID,
                new ErrorField("fecha","El rango de fecha es inválido." )
        );
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    @Override
    public boolean isBroken() {
        return !fechaInicio.isBefore(fechaFin) || fechaFin.isBefore(LocalDate.now());
    }
}
