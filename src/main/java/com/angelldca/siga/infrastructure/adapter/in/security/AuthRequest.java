package com.angelldca.siga.infrastructure.adapter.in.security;


import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
