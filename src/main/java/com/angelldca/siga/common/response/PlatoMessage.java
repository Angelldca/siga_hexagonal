package com.angelldca.siga.common.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoMessage<T> implements IResponse{
    private T id;
    private String message;

    public PlatoMessage(T id, String message) {
        this.id = id;
        this.message = message;
    }
}
