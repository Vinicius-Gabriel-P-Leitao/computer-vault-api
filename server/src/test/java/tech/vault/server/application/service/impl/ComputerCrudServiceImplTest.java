package tech.vault.server.application.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import tech.vault.server.application.dto.computer.ComputerRequestBuilder;
import tech.vault.server.application.dto.computer.info.GeneralDataBuilder;
import tech.vault.server.application.dto.computer.info.HardwareBuilder;
import tech.vault.server.application.dto.computer.info.SoftwareBuilder;
import tech.vault.server.application.service.security.UserService;
import tech.vault.server.application.service.strategy.ComputerCrudServiceImpl;
import tech.vault.server.application.service.strategy.crud.ComputerCreationStrategy;
import tech.vault.server.application.service.strategy.crud.ComputerDeletionStrategy;
import tech.vault.server.application.service.strategy.crud.ComputerReadStrategy;
import tech.vault.server.application.service.strategy.crud.ComputerUpdateStrategy;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.entity.values.*;
import tech.vault.server.domain.repository.ComputerRepository;

import java.util.UUID;

import static org.mockito.Mockito.*;

class ComputerCrudServiceImplTest {
    @Mock
    private ComputerRepository repository;

    @Mock
    private UserService userService;

    @Mock
    private ComputerCreationStrategy computerCreationStrategy;

    @Mock
    private ComputerReadStrategy computerReadStrategy;

    @Mock
    private ComputerUpdateStrategy computerUpdateStrategy;

    @Mock
    private ComputerDeletionStrategy computerDeletionStrategy;

    @Autowired
    @InjectMocks
    private ComputerCrudServiceImpl service;

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

        verify(computerCreationStrategy, times(1)).insertComputer(any());
    }
}
