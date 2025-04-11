package com.angelldca.siga.infrastructure.adapter.in.rest;


import com.angelldca.siga.application.port.in.command.auth.RefreshTokenRequestCommand;
import com.angelldca.siga.application.service.AuthService;
import com.angelldca.siga.common.response.AuthResponse;
import com.angelldca.siga.application.port.in.command.auth.AuthRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

   /* @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }*/
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request); // Genera accessToken y refreshToken

        // Crear la cookie con el refresh token
        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", response.getRefreshToken())
                .httpOnly(true)
                .secure(false) // ⚠️ true en producción (requiere HTTPS)
                .path("/api/auth/refresh")
                .sameSite("Lax")
                .maxAge(Duration.ofDays(7)) // duración de la cookie
                .build();

        // Devolver la cookie + el access token (el refresh NO va en el body)
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                .body(new AuthResponse(response.getToken(), null));
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@CookieValue("refresh_token") String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }
}
