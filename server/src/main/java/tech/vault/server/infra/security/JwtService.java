package tech.vault.server.infra.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String extractUserName(String token) {
        return null;
    }

    private Claims extractAllClaims(String token) {}
}
