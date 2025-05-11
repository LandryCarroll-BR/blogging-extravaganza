package com.smartstore.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Allow H2 Console access without auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        // You can allow public APIs here too
                        .requestMatchers(new AntPathRequestMatcher("/api/public/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        // Disable CSRF for H2 Console only
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                )
                .headers(headers -> headers
                        // Allow H2 Console to load in an iframe
                        .frameOptions().disable()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}

