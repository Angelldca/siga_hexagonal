package com.angelldca.siga.infrastructure.adapter.in.security;


import com.angelldca.siga.application.port.out.user.GetByEmailPort;
import com.angelldca.siga.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final GetByEmailPort getByEmailPort;

    public CustomUserDetailsService(GetByEmailPort getByEmailPort) {
        this.getByEmailPort = getByEmailPort;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getByEmailPort.findByEmail(email);
        return new CustomUserDetails(user);
    }
}