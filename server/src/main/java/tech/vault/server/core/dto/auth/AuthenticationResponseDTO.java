package tech.vault.server.core.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationResponseDTO(
        @JsonProperty("access_token") String token
) {
}
