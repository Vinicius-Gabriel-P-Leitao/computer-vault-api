package tech.vault.server.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // *
    // Note: Extrai as claims do token
    // *

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsTFunction
    ) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // *
    // NOTE: Gera o token JWT
    // *

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        Date now = new Date();

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(now.getTime()))
                .setExpiration(new Date(now.getTime() * 20000L))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // *
    // NOTE: Validações feitas
    // INFO: Se o token está expirado
    // *

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);

        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
