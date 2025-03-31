package com.angelldca.siga.domain.rule.evento;

import com.angelldca.siga.common.exception.ErrorField;
import com.angelldca.siga.domain.rule.BrokenRule;
import com.angelldca.siga.domain.rule.DomainErrorMessage;

public class EventNameNotNullRule  extends BrokenRule {
    private final String name;
    public EventNameNotNullRule(String name) {
        super(
                DomainErrorMessage.OBJECT_NOT_NULL,
                new ErrorField("nombre", "El nombre del evento es obligatorio.")
        );
        this.name = name;
    }

    @Override
    public boolean isBroken() {
        return this.name == null || this.name.trim().isEmpty();
    }
}

