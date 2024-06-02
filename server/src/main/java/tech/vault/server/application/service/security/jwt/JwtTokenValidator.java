package tech.vault.server.application.service.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtTokenValidator {
    boolean isValidToken(String token, UserDetails userDetails);

    String extractUserName(String token);

    <T> T extractClaim(
            String token,
            Function<Claims, T> claimsTFunction
    );
}
