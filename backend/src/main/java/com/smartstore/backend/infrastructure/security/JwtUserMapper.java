package com.smartstore.backend.infrastructure.security;

import com.smartstore.backend.domain.model.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtUserMapper {

    public User fromJwt(Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        String name = jwt.getClaimAsString("name");
        String picture = jwt.getClaimAsString("picture");

        return new User(email, name, picture);
    }
}
