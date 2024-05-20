package tech.vault.server.core.dto.auth;

import lombok.Builder;

@Builder
public record AuthenticationRequest(String userName, String password) {
}
