package tech.vault.server.application.dto.computer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import tech.vault.server.application.dto.computer.info.GeneralDataBuilder;
import tech.vault.server.application.dto.computer.info.HardwareBuilder;
import tech.vault.server.application.dto.computer.info.SoftwareBuilder;

@Builder
public record ComputerRequestBuilder(@JsonProperty("dados-gerais") GeneralDataBuilder generalData,
                                     @JsonProperty("hardware") HardwareBuilder hardware,
                                     @JsonProperty("software") SoftwareBuilder software) {
}
