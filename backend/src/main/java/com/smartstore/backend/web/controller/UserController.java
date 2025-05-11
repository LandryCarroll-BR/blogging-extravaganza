package com.smartstore.backend.web.controller;

import com.smartstore.backend.application.service.UserProfileService;
import com.smartstore.backend.domain.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserProfileService userProfileService;

    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/profile")
    public User profile(@AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt);
        return userProfileService.getUserProfile(jwt);
    }
}