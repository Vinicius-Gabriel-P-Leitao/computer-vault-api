package tech.vault.server.core.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import tech.vault.server.core.dto.computer.ComputerRequestBuilder;
import tech.vault.server.core.dto.info.GeneralDataBuilder;
import tech.vault.server.core.dto.info.HardwareBuilder;
import tech.vault.server.core.dto.info.SoftwareBuilder;
import tech.vault.server.core.service.ComputerService;
import tech.vault.server.core.service.UserService;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.entity.values.*;
import tech.vault.server.domain.repository.ComputerRepository;
import tech.vault.server.infra.exception.ExArgumentNotValid;
import tech.vault.server.infra.exception.ExNotFound;
import tech.vault.server.infra.exception.handlers.ExArgumentNotValidExceptionHandler;

import java.util.UUID;

import static org.mockito.Mockito.*;

class ComputerServiceImplTest {
    @Mock
    private ComputerRepository repository;
    @Mock
    private UserService userService;
    @Autowired
    @InjectMocks
    private ComputerServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Sucesso ao criar computador")
    void setComputerSuccess() {
        User user = new User(UUID.randomUUID(), "User", "User", Role.USER);

        when(userService.userIsPresent("User")).thenReturn(user);

        ComputerRequestBuilder computerRequestBuilder = new ComputerRequestBuilder(
                new GeneralDataBuilder("User", "Unidade", "Coleta", "001122", LocationComputer.ESTOQUE),
                new HardwareBuilder("DELL", TypeComputer.ALL_IN_ONE, "PC01", "192.168.0.122", "I5-8500", 8, 2400, TypeRam.DDR3, ModelRam.DIMM, 2, 1000, 256),
                new SoftwareBuilder(So.W10)
        );

        service.setComputer(computerRequestBuilder);

        verify(repository, times(1)).save(any());
    }
}