package com.angelldca.siga.common.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message<T> implements IResponse{
    private T id;
    private String message;

    public Message(T id, String message) {
        this.id = id;
        this.message = message;
    }
}
