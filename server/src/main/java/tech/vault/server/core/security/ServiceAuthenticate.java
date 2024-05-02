package tech.vault.server.core.security;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ServiceAuthenticate {
    private final ServiceJwt serviceJwt;

    public ServiceAuthenticate(ServiceJwt serviceJwt) {
        this.serviceJwt = serviceJwt;
    }

    // NOTE: Recebe a autenticação e valida com token gerado
    public String authenticate(Authentication authentication) {
        return serviceJwt.getGenerateToken(authentication);
    }
}