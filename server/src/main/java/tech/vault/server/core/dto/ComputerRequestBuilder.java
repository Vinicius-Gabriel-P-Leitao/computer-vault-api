package tech.vault.server.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import tech.vault.server.core.dto.info.GeneralDataBuilder;
import tech.vault.server.core.dto.info.HardwareBuilder;
import tech.vault.server.core.dto.info.SoftwareBuilder;

@Builder
public record ComputerRequestBuilder(@JsonProperty("dados-gerais") GeneralDataBuilder generalData,
                                     @JsonProperty("hardware") HardwareBuilder hardware,
                                     @JsonProperty("software") SoftwareBuilder software) {
}
