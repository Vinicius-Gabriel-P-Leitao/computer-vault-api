package tech.vault.server.application.service.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Map;

public interface JwtTokenGenerator {
    String generateToken(UserDetails userDetails);

    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    Key getSecretKey();
}
