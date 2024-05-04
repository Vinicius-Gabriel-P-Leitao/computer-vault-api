package tech.vault.server.core.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.entity.values.LocationComputer;

import java.util.UUID;

@Builder
public record GeneralDataBuilder(@JsonProperty("identificador") UUID computerId,
                                 @JsonProperty("quem-adicionou") User user,
                                 @JsonProperty("condições") String computerCondition,
                                 @JsonProperty("unidade-de-negocio") String businessUnit,
                                 @JsonProperty("departamento") String department,
                                 @JsonProperty("numero-patrimonio") String numberPatrimony,
                                 @JsonProperty("local") LocationComputer locationComputer) {
    public GeneralDataBuilder(Computer computer) {
        this(
                computer.getComputerId(), computer.getUser(), computer.getComputerCondition(),
                computer.getBusinessUnit(), computer.getDepartment(), computer.getNumberPatrimony(),
                computer.getLocationComputer()
        );
    }
}
