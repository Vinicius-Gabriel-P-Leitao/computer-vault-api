package tech.vault.server.core.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.LocationComputer;

@Builder
public record GeneralDataBuilder(
        @NotBlank(message = "O usuário não pode ser vazio")
        @JsonProperty("quem-adicionou")
        String user,

        @NotNull(message = "A unidade de negocio não pode ser nula")
        @JsonProperty("unidade-de-negocio")
        String businessUnit,

        @NotBlank(message = "O departamento não pode ser vazio")
        @JsonProperty("departamento")
        String department,

        @JsonProperty("numero-patrimonio")
        String numberPatrimony,

        @NotNull(message = "O local do computador não pode ser nulo")
        @JsonProperty("local")
        LocationComputer locationComputer) {

    public GeneralDataBuilder(Computer computer) {
        this(
                computer.getUser(),
                computer.getBusinessUnit(),
                computer.getDepartment(),
                computer.getNumberPatrimony(),
                computer.getLocationComputer()
        );
    }
}
