package tech.vault.server.infra.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    public String extractUserName(String token);

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsTFunction
    );

    public String generateToken(UserDetails userDetails);

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    public boolean isValidToken(String token, UserDetails userDetails);
}
