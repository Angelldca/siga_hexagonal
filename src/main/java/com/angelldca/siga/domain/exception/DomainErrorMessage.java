package com.angelldca.siga.domain.exception;

public enum DomainErrorMessage {
    OBJECT_NOT_FOUND(1, Series.DOMAIN_ERROR, "Pacientes no encontrados."),
    QUALIFICATION_NOT_FOUND(2, Series.DOMAIN_ERROR, "Calificación no encontrada.");

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
