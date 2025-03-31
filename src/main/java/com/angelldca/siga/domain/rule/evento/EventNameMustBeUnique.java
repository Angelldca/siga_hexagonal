package com.angelldca.siga.domain.rule.evento;

import com.angelldca.siga.application.port.out.evento.CheckEventUniquePort;
import com.angelldca.siga.common.exception.ErrorField;
import com.angelldca.siga.domain.rule.BrokenRule;
import com.angelldca.siga.domain.rule.DomainErrorMessage;

import java.time.LocalDateTime;

public class EventNameMustBeUnique<T> extends BrokenRule {
    private final String name;
    private final LocalDateTime fechaInicio;
    private final LocalDateTime fechaFin;
    private final T id;
    private final CheckEventUniquePort checkEventUniquePort;

    public EventNameMustBeUnique(String name, LocalDateTime fechaInicio, LocalDateTime fechaFin, T id, CheckEventUniquePort checkEventUniquePort) {
        super(
                DomainErrorMessage.OBJECT_MUST_BY_UNIQUE,
                new ErrorField("nombre", "El nombre del evento debe ser único.")

        );
        this.name = name;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.id = id;
        this.checkEventUniquePort = checkEventUniquePort;
    }

    @Override
    public boolean isBroken() {
        return this.checkEventUniquePort.existsByNameAndDateRange(name,fechaInicio,fechaFin,id);
    }
}
