package tech.vault.server.application.service.security;

import tech.vault.server.application.dto.auth.AuthenticationRequestDTO;
import tech.vault.server.application.dto.auth.AuthenticationResponseDTO;
import tech.vault.server.application.dto.auth.RegisterRequestDTO;
import tech.vault.server.domain.entity.User;

public interface UserService {
    public AuthenticationResponseDTO userRegister(RegisterRequestDTO request);

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);

    public User userIsPresent(String userName);
}
