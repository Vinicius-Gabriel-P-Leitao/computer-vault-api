package tech.vault.server.infra.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceJwt {
    @Autowired
    private final JwtEncoder encoder;

    public String getGenerateToken(Authentication authentication) {
        // NOTE: Coloca os papeis do escopo no token
        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("spring-security-jwt")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600L))
                .subject(authentication.getName())
                .claim("scopes", scopes)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}