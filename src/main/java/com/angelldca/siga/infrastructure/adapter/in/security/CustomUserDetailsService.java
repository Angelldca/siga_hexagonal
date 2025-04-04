package com.angelldca.siga.infrastructure.adapter.in.security;


import com.angelldca.siga.application.port.out.GetPort;
import com.angelldca.siga.application.port.out.user.GetByEmailPort;
import com.angelldca.siga.application.port.out.user_permission_business.GetAllByUserIdPort;
import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.domain.model.UserPermissionBusiness;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final GetByEmailPort getByEmailPort;
    private final GetAllByUserIdPort getUserPermissionBusinessPort;

    public CustomUserDetailsService(GetByEmailPort getByEmailPort,
                                    GetAllByUserIdPort getUserPermissionBusinessPort) {
        this.getByEmailPort = getByEmailPort;
        this.getUserPermissionBusinessPort = getUserPermissionBusinessPort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByEmailPort.findByEmail(username);

        List<UserPermissionBusiness> permisos = getUserPermissionBusinessPort.getAllByUserId(user.getId());
        user.setUserPermissionBusinesses(permisos);

        return new CustomUserDetails(user);
    }
}