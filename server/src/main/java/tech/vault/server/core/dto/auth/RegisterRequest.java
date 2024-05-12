package tech.vault.server.core.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import tech.vault.server.domain.entity.values.Role;

@Builder
public record RegisterRequest(
        @JsonProperty("user-name") String userName,
        String password,
        Role role) {
}
