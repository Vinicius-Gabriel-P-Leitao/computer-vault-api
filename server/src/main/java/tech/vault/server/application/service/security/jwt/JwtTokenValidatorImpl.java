package tech.vault.server.application.service.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tech.vault.server.infra.exception.ExJwtExpired;

import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenValidatorImpl implements JwtTokenValidator {
    private final JwtTokenGenerator jwtTokenGenerator;

    //INFO: Validação direta do token

    /**
     * @param token       verifica se o token é valido testando o nome de usuário dentro do token e a validade
     * @param userDetails pega os detalhes do usuário para gerar um token baseado em suas roles e nome de usuário
     * @return boolean retorna se o usuário é valido ou não
     */
    @Override
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

    //INFO: Extração de valores do token

    /**
     * @param token           Extrai todos os dados do token
     * @param claimsTFunction função com claims e outro valor genérico
     * @return <T> Retorna um valor genérico
     */
    @Override
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsTFunction
    ) {
        final Claims claims;

        try {
            claims = extractAllClaims(token);
        } catch (ExpiredJwtException exception) {
            throw new ExJwtExpired("O seu token está expirado");
        }

        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(jwtTokenGenerator.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * @param token Extrai o nome de usuário do token e retorna em formato de string
     * @return String
     */
    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

}
