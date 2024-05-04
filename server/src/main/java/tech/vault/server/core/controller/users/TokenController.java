package tech.vault.server.core.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vault.server.infra.security.ServiceAuthenticate;

@RestController
public class TokenController {
    @Autowired
    private ServiceAuthenticate serviceAuthenticate;

    @PostMapping("/authenticate")
    public String authenticate(Authentication authentication) {
        return serviceAuthenticate.authenticate(authentication);
    }
}
