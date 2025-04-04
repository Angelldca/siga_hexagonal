package com.angelldca.siga.application.port.in.command.auth;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequestCommand {
    private String refreshToken;
}
