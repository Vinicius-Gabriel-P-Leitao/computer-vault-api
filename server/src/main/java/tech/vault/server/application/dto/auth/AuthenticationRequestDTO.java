package tech.vault.server.application.dto.auth;

import lombok.Builder;

@Builder
public record AuthenticationRequestDTO(String userName, String password) {
}
