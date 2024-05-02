package tech.vault.server.core.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vault.server.core.security.ServiceAuthenticate;

@RestController
public class TokenController {
    @Autowired
    private ServiceAuthenticate serviceAuthenticate;

    @PostMapping("/authenticate")
    public String authenticate(Authentication authentication) {
        return serviceAuthenticate.authenticate(authentication);
    }
}
