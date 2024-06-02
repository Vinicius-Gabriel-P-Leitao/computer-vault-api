package tech.vault.server.application.dto.computer.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.ModelRam;
import tech.vault.server.domain.entity.values.TypeComputer;
import tech.vault.server.domain.entity.values.TypeRam;
import tech.vault.server.infra.validation.Ipv4Tester;

@Builder
public record HardwareBuilder(
        @JsonProperty("marca-computador")
        String computerBrand,

        @NotNull(message = "O tipo do computador não pode ser nulo")
        @JsonProperty("tipo-computador")
        TypeComputer typeComputer,

        @NotBlank(message = "O nome do computador não pode ser vazio")
        @JsonProperty("nome")
        String nameComputer,

        @NotBlank(message = "O ip não pode ser nulo")
        @Ipv4Tester(message = "Endereço IP inválido")
        @JsonProperty("ip")
        String ip,

        @JsonProperty("processador")
        String cpu,

        @JsonProperty("memoria-ram")
        Integer memoryRam,

        @JsonProperty("frequência-ram")
        Integer frequencyRam,

        @NotNull(message = "O tipo da memoria não pode ser nulo")
        @JsonProperty("tipo-ram")
        TypeRam typeRam,

        @NotNull(message = "O modelo da memoria ram não pode ser nulo")
        @JsonProperty("modelo-ram")
        ModelRam modelRam,

        @JsonProperty("quantidade-instalada")
        Integer amountOfRamInstalled,

        @JsonProperty("HD")
        Integer hd,

        @JsonProperty("SSD")
        Integer ssd) {

    public HardwareBuilder(Computer computer) {
        this(
                computer.getComputerBrand(),
                computer.getTypeComputer(),
                computer.getNameComputer(),
                computer.getIp(),
                computer.getCpu(),
                computer.getMemoryRam(),
                computer.getFrequencyRam(),
                computer.getTypeRam(),
                computer.getModelRam(),
                computer.getAmountOfRamInstalled(),
                computer.getHd(),
                computer.getSsd()
        );
    }
}
