package tech.vault.server.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.vault.server.application.dto.computer.ComputerRequestBuilder;
import tech.vault.server.application.dto.computer.ComputerResponseBuilder;
import tech.vault.server.application.service.strategy.crud.ComputerCreationStrategy;
import tech.vault.server.application.service.strategy.crud.ComputerDeletionStrategy;
import tech.vault.server.application.service.strategy.crud.ComputerReadStrategy;
import tech.vault.server.application.service.strategy.crud.ComputerUpdateStrategy;

@Service
@RequiredArgsConstructor
public class ComputerCrudServiceImpl implements ComputerCrudService {
    private final ComputerCreationStrategy computerCreationStrategy;
    private final ComputerReadStrategy computerReadStrategy;
    private final ComputerUpdateStrategy computerUpdateStrategy;
    private final ComputerDeletionStrategy computerDeletionStrategy;

    @Override
    public Page<ComputerResponseBuilder> getAllComputers(Pageable pageable) {
        return computerReadStrategy.selectAllPageable(pageable);
    }

    @Override
    public ComputerResponseBuilder getComputerById(Integer id) {
        return computerReadStrategy.selectById(id);
    }

    @Override
    @Transactional
    public void setComputer(ComputerRequestBuilder request) {
        computerCreationStrategy.insertComputer(request);
    }

    @Override
    @Transactional
    public void patchComputer(Integer id, ComputerRequestBuilder request) {
        computerUpdateStrategy.updateComputer(id, request);
    }

    @Override
    public void deleteComputer(Integer id) {
        computerDeletionStrategy.deleteComputer(id);
    }
}
