package com.angelldca.siga.domain.rule;

public enum DomainErrorMessage {
    OBJECT_NOT_FOUND(1, Series.DOMAIN_ERROR, "Objeto no encontrado."),
    OBJECT_NOT_NULL(2, Series.DOMAIN_ERROR, "Objeto no nulo."),
    OBJECT_MUST_BY_UNIQUE(3, Series.DOMAIN_ERROR, "Objeto debe ser único."),
    OBJECT_INVALID(4, Series.DOMAIN_ERROR, "Objeto inválido.");

    private final int code;
    private final Series series;
    private final String message;

    DomainErrorMessage(int code, Series series, String message) {
        this.code = code;
        this.series = series;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public Series getSeries() {
        return series;
    }

    public String getMessage() {
        return message;
    }

    public enum Series {
        DOMAIN_ERROR
    }
}
