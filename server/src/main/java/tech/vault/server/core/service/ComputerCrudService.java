package tech.vault.server.core.service;

import tech.vault.server.core.dto.ComputerRequestBuilder;
import tech.vault.server.core.dto.ComputerResponseBuilder;

import java.util.List;
import java.util.UUID;

public interface ComputerCrudService {
    public List<ComputerResponseBuilder> getAllComputers();
    public ComputerResponseBuilder getComputerById(UUID id);
    public void setComputer(ComputerRequestBuilder request);
    public void patchComputer(UUID id, ComputerRequestBuilder request);
    public void deleteComputer(UUID id);
}
