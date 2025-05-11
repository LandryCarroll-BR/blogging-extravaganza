package com.smartstore.backend.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.*;

@Configuration
public class CustomJwtDecoder {

    @Bean
    public JwtDecoder jwtDecoder() {
        // Google's issuer and JWK Set URI (for verifying signature)
        String issuer = "https://accounts.google.com";
        String jwkSetUri = "https://www.googleapis.com/oauth2/v3/certs";

        NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();

        // Optional: Validate expected issuer
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);

        // Optional: Validate audience if you want to check client ID
        OAuth2TokenValidator<Jwt> withAudience = jwt -> {
            String aud = jwt.getAudience().stream().findFirst().orElse("");
            if (!aud.equals("555123116335-kk956h3sqk14n4tjsqan6jthrgth03vt.apps.googleusercontent.com")) {
                return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token", "Invalid audience", null));
            }
            return OAuth2TokenValidatorResult.success();
        };

        // Combine validators
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withIssuer, withAudience);
        decoder.setJwtValidator(validator);

        return decoder;
    }
}
