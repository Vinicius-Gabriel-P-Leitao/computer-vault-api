package tech.vault.server.core.service;

import tech.vault.server.core.dto.auth.AuthenticationRequestDTO;
import tech.vault.server.core.dto.auth.AuthenticationResponseDTO;
import tech.vault.server.core.dto.auth.RegisterRequestDTO;
import tech.vault.server.domain.entity.User;

public interface UserService {
    public AuthenticationResponseDTO userRegister(RegisterRequestDTO request);

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);

    public User userIsPresent(String userName);
}
