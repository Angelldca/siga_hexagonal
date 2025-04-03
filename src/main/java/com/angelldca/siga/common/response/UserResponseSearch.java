package com.angelldca.siga.common.response;

import com.angelldca.siga.domain.model.User;
import com.angelldca.siga.infrastructure.adapter.out.persistence.usuario.EUserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
public class UserResponseSearch {
    private UUID id;
    private String username;
    private String email;
    private String image;
    private EUserType type;
    public UserResponseSearch(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.image = user.getImage();
        this.type = user.getType();

    }
}
