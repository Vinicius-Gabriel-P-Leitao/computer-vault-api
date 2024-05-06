package tech.vault.server.core.dto.authenticate;

public record AuthenticationRequest(String userName, String password) {
}
