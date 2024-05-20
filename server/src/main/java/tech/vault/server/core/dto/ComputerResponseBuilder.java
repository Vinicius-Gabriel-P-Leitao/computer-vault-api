package tech.vault.server.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Builder;
import tech.vault.server.core.dto.info.GeneralDataBuilder;
import tech.vault.server.core.dto.info.HardwareBuilder;
import tech.vault.server.core.dto.info.SoftwareBuilder;
import tech.vault.server.domain.entity.Computer;

@Builder
public record ComputerResponseBuilder(
        @JsonProperty("identificador") Integer computerId,
        @JsonProperty("dados-gerais") @Valid GeneralDataBuilder generalData,
        @JsonProperty("hardware") @Valid HardwareBuilder hardware,
        @JsonProperty("software") @Valid SoftwareBuilder software) {

    public ComputerResponseBuilder(Computer computer) {
        this(
                computer.getComputerId(),
                new GeneralDataBuilder(computer),
                new HardwareBuilder(computer),
                new SoftwareBuilder(computer)
        );
    }
}
