package tech.vault.server.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.vault.server.application.dto.computer.ComputerRequestBuilder;
import tech.vault.server.application.dto.computer.ComputerResponseBuilder;

public interface ComputerCrudService {
    Page<ComputerResponseBuilder> getAllComputers(Pageable pageable);

    ComputerResponseBuilder getComputerById(Integer id);

    void setComputer(ComputerRequestBuilder request);

    void patchComputer(Integer id, ComputerRequestBuilder request);

    void deleteComputer(Integer id);
}
