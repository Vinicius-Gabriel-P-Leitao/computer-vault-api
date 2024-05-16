package tech.vault.server.core.service;

import tech.vault.server.core.dto.ComputerRequestBuilder;
import tech.vault.server.core.dto.ComputerResponseBuilder;

import java.util.List;
import java.util.UUID;

public interface ComputerCrudService {
    public List<ComputerResponseBuilder> getAllComputers();
    public ComputerResponseBuilder getComputerById(Integer id);
    public void setComputer(ComputerRequestBuilder request);
    public void patchComputer(Integer id, ComputerRequestBuilder request);
    public void deleteComputer(Integer id);
}
