package tech.vault.server.application.service.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenGeneratorImpl implements JwtTokenGenerator {
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * @param userDetails pega os detalhes do usuário para gerar um token baseado em suas roles e nome de usuário
     * @return String retorna o token de acesso
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * @param extraClaims Parâmetro para montar o json do token
     * @param userDetails Pega os detalhes do usuário para gerar um token baseado em suas roles e nome de usuário
     * @return String retorna o token de acesso
     */
    @Override
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.YEAR, 1);
        Date nextYear = calendar.getTime();

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(today)
                .setExpiration(nextYear)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * @return Key retorna a chave RSA
     */
    @Override
    public Key getSecretKey() {
        return secretKey;
    }
}
