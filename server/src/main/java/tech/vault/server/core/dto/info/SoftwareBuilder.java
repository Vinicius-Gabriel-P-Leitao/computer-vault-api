package tech.vault.server.core.dto.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.So;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SoftwareBuilder(@JsonProperty("sistema-operacional") So so) {
    public SoftwareBuilder(Computer computer) {
        this (computer.getNameSo());
    }
}
