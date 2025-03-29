package com.angelldca.siga.domain.rule.Plato;

import com.angelldca.siga.domain.rule.BrokenRule;
import com.angelldca.siga.common.exception.ErrorField;
import com.angelldca.siga.domain.rule.DomainErrorMessage;

public class PlatoNameNotNullRule extends BrokenRule {
    private final String name;
    public PlatoNameNotNullRule(String name) {
        super(
                DomainErrorMessage.OBJECT_NOT_NULL,
                new ErrorField("nombre", "El nombre del plato es obligatorio.")
        );
        this.name = name;
    }

    @Override
    public boolean isBroken() {
        return this.name == null || this.name.trim().isEmpty();
    }
}
