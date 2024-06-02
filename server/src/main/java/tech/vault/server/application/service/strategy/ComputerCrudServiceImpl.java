package tech.vault.server.application.service.strategy;

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

    /**
     * @param pageable Busca dentro do banco todos os computadores de forma paginada
     * @return ComputerResponseBuilder
     */
    @Override
    public Page<ComputerResponseBuilder> getAllComputers(Pageable pageable) {
        return computerReadStrategy.selectAllPageable(pageable);
    }

    /**
     * @param id Busca dentro do banco um computador baseado em seu ID
     * @return ComputerResponseBuilder
     */
    @Override
    public ComputerResponseBuilder getComputerById(Integer id) {
        return computerReadStrategy.selectById(id);
    }

    /**
     * @param request Insere dentro do banco de dados um computador baseado em um DTO
     */
    @Override
    @Transactional
    public void setComputer(ComputerRequestBuilder request) {
        computerCreationStrategy.insertComputer(request);
    }

    /**
     * @param id      Pesquisa computador baseado em ID
     * @param request Altera o computador achado baseado em um DTO
     */
    @Override
    @Transactional
    public void patchComputer(Integer id, ComputerRequestBuilder request) {
        computerUpdateStrategy.updateComputer(id, request);
    }

    /**
     * @param id Deleta um computador dentro do banco de dados
     */
    @Override
    public void deleteComputer(Integer id) {
        computerDeletionStrategy.deleteComputer(id);
    }
}
