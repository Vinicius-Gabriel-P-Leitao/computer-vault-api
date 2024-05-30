package tech.vault.server.core.dto.computer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import tech.vault.server.core.controller.computer.ComputerCrudController;
import tech.vault.server.core.dto.info.GeneralDataBuilder;
import tech.vault.server.core.dto.info.HardwareBuilder;
import tech.vault.server.core.dto.info.SoftwareBuilder;
import tech.vault.server.domain.entity.Computer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Builder
public record ComputerResponseBuilder(
        @JsonProperty("identificador") Integer computerId,
        @JsonProperty("dados-gerais") @Valid GeneralDataBuilder generalData,
        @JsonProperty("hardware") @Valid HardwareBuilder hardware,
        @JsonProperty("software") @Valid SoftwareBuilder software,
        @JsonProperty("link-direto") String selfLink) {

    public ComputerResponseBuilder(Computer computer) {
        this(
                computer.getComputerId(),
                new GeneralDataBuilder(computer),
                new HardwareBuilder(computer),
                new SoftwareBuilder(computer),
                WebMvcLinkBuilder.linkTo(methodOn(ComputerCrudController.class).getComputerById(computer.getComputerId())).toString()
        );
    }
}
