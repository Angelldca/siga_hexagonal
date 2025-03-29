package com.angelldca.siga.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class ErrorField {
    private String field;
    private String message;

    public ErrorField(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
