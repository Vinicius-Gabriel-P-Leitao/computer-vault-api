package tech.vault.server.core.service;

import tech.vault.server.core.dto.auth.AuthenticationRequest;
import tech.vault.server.core.dto.auth.AuthenticationResponse;
import tech.vault.server.core.dto.auth.RegisterRequest;

public interface UserService {
    public AuthenticationResponse userRegister(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
