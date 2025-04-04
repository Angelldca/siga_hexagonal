package com.angelldca.siga.application.service;


import com.angelldca.siga.application.port.in.command.auth.RefreshTokenRequestCommand;
import com.angelldca.siga.common.anotations.UseCase;
import com.angelldca.siga.common.response.AuthResponse;
import com.angelldca.siga.application.port.in.command.auth.AuthRequest;
import com.angelldca.siga.infrastructure.adapter.in.security.CustomUserDetailsService;
import com.angelldca.siga.infrastructure.config.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@UseCase
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtTokenProvider jwtTokenProvider, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtTokenProvider.generateToken(userDetails);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refresh(RefreshTokenRequestCommand request) {
        String refreshToken = request.getRefreshToken();
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("Refresh token inválido o expirado");
        }

        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String newAccessToken = jwtTokenProvider.generateToken(userDetails);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}
