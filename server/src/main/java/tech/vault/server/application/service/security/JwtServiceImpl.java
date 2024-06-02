package tech.vault.server.application.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.vault.server.application.service.security.jwt.JwtTokenGenerator;
import tech.vault.server.application.service.security.jwt.JwtTokenValidator;
import tech.vault.server.application.service.security.jwt.JwtTokenValidatorImpl;
import tech.vault.server.infra.exception.ExJwtExpired;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtTokenGenerator jwtTokenGenerator;
    private final JwtTokenValidator jwtTokenValidator;

    /**
     * @param token Extrai o nome de usuário do token e retorna em formato de string
     * @return String
     */
    @Override
    public String extractUserName(String token) {
        return jwtTokenValidator.extractUserName(token);
    }

    /**
     * @param token           Extrai todos os dados do token
     * @param claimsTFunction função com claims e outro valor genérico
     * @return <T> Retorna um valor genérico
     */
    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        return jwtTokenValidator.extractClaim(token, claimsTFunction);
    }

    /**
     * @param token       verifica se o token é valido testando o nome de usuário dentro do token e a validade
     * @param userDetails pega os detalhes do usuário para gerar um token baseado em suas roles e nome de usuário
     * @return boolean retorna se o usuário é valido ou não
     */
    @Override
    public boolean isValidToken(String token, UserDetails userDetails) {
        return jwtTokenValidator.isValidToken(token, userDetails);
    }

    /**
     * @param userDetails pega os detalhes do usuário para gerar um token baseado em suas roles e nome de usuário
     * @return String retorna o token de acesso
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        return jwtTokenGenerator.generateToken(userDetails);
    }

    /**
     * @param extraClaims Parâmetro para montar o json do token
     * @param userDetails Pega os detalhes do usuário para gerar um token baseado em suas roles e nome de usuário
     * @return String retorna o token de acesso
     */
    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return jwtTokenGenerator.generateToken(extraClaims, userDetails);
    }
}