package tech.vault.server.core.dto.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.entity.values.LocationComputer;

import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record GeneralDataBuilder(
        @JsonProperty("quem-adicionou") String user,
        @JsonProperty("condições") String computerCondition,
        @JsonProperty("unidade-de-negocio") String businessUnit,
        @JsonProperty("departamento") String department,
        @JsonProperty("numero-patrimonio") String numberPatrimony,
        @JsonProperty("local") LocationComputer locationComputer) {
    public GeneralDataBuilder(Computer computer) {
        this(
                computer.getUser(), computer.getComputerCondition(),
                computer.getBusinessUnit(), computer.getDepartment(), computer.getNumberPatrimony(),
                computer.getLocationComputer()
        );
    }
}
