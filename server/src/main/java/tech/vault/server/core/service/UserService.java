package tech.vault.server.core.service;

import tech.vault.server.core.dto.auth.AuthenticationRequestDTO;
import tech.vault.server.core.dto.auth.AuthenticationResponseDTO;
import tech.vault.server.core.dto.auth.RegisterRequestDTO;

public interface UserService {
    public AuthenticationResponseDTO userRegister(RegisterRequestDTO request);

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
}
