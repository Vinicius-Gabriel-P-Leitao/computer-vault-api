package tech.vault.server.application.service.strategy.crud;

import tech.vault.server.application.dto.computer.ComputerRequestBuilder;

public interface ComputerUpdateStrategy {
     void updateComputer(Integer id, ComputerRequestBuilder request);
}
