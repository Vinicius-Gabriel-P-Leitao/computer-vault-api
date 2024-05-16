package tech.vault.server.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import tech.vault.server.core.dto.info.GeneralDataBuilder;
import tech.vault.server.core.dto.info.HardwareBuilder;
import tech.vault.server.core.dto.info.SoftwareBuilder;
import tech.vault.server.domain.entity.Computer;

@Builder
public record ComputerResponseBuilder(@JsonProperty("indentificador") Integer computerId,
                                      @JsonProperty("dados-gerais") GeneralDataBuilder generalData,
                                      @JsonProperty("hardware") HardwareBuilder hardware,
                                      @JsonProperty("software") SoftwareBuilder software) {
    public ComputerResponseBuilder(Computer computer) {
        this(
                computer.getComputerId(),
                new GeneralDataBuilder(computer.getUser(), computer.getComputerCondition(),
                        computer.getBusinessUnit(), computer.getDepartment(), computer.getNumberPatrimony(),
                        computer.getLocationComputer()
                ),
                new HardwareBuilder(computer.getComputerBrand(), computer.getTypeComputer(), computer.getNameComputer(),
                        computer.getIp(), computer.getCpu(), computer.getMemoryRam(),
                        computer.getFrequencyRam(), computer.getTypeRam(), computer.getModelRam(),
                        computer.getAmountOfRamInstalled(), computer.getHd(), computer.getSsd()
                ),
                new SoftwareBuilder(computer.getNameSo())
        );
    }
}
