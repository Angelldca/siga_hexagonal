package com.angelldca.siga.domain.rule;

import com.angelldca.siga.common.exception.BusinessRuleValidationException;

public final class RulesChecker {

    private RulesChecker() {
    }

    public static void checkRule(BrokenRule rule) {
        if (rule.isBroken()) {
            throw new BusinessRuleValidationException(rule);
        }
    }
}
