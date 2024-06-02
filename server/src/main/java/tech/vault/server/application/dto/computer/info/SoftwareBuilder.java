package tech.vault.server.application.dto.computer.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.So;

@Builder
public record SoftwareBuilder(
        @NotNull(message = "O sistema operacional não pode ser nulo")
        @JsonProperty("sistema-operacional")
        So so) {

    public SoftwareBuilder(Computer computer) {
        this(computer.getNameSo());
    }
}
