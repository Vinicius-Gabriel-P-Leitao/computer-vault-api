package tech.vault.server.core.dto.auth;

public record AuthenticationRequest(String userName, String password) {
}
