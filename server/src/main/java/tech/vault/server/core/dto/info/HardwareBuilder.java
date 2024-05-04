package tech.vault.server.core.dto.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.ModelRam;
import tech.vault.server.domain.entity.values.TypeComputer;
import tech.vault.server.domain.entity.values.TypeRam;

@Builder
public record HardwareBuilder(@JsonProperty("marca-computador") String computerBrand,
                              @JsonProperty("tipo-computador") TypeComputer typeComputer,
                              @JsonProperty("nome") String nameComputer,
                              @JsonProperty("ip") String ip,
                              @JsonProperty("processador") String cpu,
                              @JsonProperty("memoria-ram") Integer memoryRam,
                              @JsonProperty("frequência-ram") Integer frequencyRam,
                              @JsonProperty("tipo-ram") TypeRam typeRam,
                              @JsonProperty("modelo-ram") ModelRam modelRam,
                              @JsonProperty("quantidade-instalada") Integer amountOfRamInstalled,
                              @JsonProperty("HD") Integer hd,
                              @JsonProperty("SSD") Integer ssd) {
    public HardwareBuilder(Computer computer) {
        this(
                computer.getComputerBrand(), computer.getTypeComputer(), computer.getNameComputer(),
                computer.getIp(), computer.getCpu(), computer.getMemoryRam(),
                computer.getFrequencyRam(), computer.getTypeRam(), computer.getModelRam(),
                computer.getAmountOfRamInstalled(), computer.getHd(), computer.getSsd()
        );
    }
}
