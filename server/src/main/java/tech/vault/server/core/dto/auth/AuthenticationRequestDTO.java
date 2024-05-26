package tech.vault.server.core.dto.auth;

import lombok.Builder;

@Builder
public record AuthenticationRequestDTO(String userName, String password) {
}
