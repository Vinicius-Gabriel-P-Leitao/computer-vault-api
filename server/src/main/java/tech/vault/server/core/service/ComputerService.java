package tech.vault.server.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.vault.server.core.dto.ComputerRequestBuilder;
import tech.vault.server.core.dto.ComputerResponseBuilder;

public interface ComputerService {
    Page<ComputerResponseBuilder> getAllComputers(Pageable pageable);
    public ComputerResponseBuilder getComputerById(Integer id);
    public void setComputer(ComputerRequestBuilder request);
    public void patchComputer(Integer id, ComputerRequestBuilder request);
    public void deleteComputer(Integer id);
}
