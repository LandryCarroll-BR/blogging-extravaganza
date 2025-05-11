package com.smartstore.backend.application.service;

import com.smartstore.backend.domain.model.User;
import com.smartstore.backend.infrastructure.security.JwtUserMapper;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final JwtUserMapper jwtUserMapper;

    public UserProfileService(JwtUserMapper jwtUserMapper) {
        this.jwtUserMapper = jwtUserMapper;
    }

    public User getUserProfile(Jwt jwt) {
        System.out.println(jwt);
        return jwtUserMapper.fromJwt(jwt);
    }
}
