package com.angelldca.siga.application.port.in.command.auth;


import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
