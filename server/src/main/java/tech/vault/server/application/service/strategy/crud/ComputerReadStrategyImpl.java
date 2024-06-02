package tech.vault.server.application.service.strategy.crud;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import tech.vault.server.application.dto.computer.ComputerResponseBuilder;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.repository.ComputerRepository;

@Component
@RequiredArgsConstructor
public class ComputerReadStrategyImpl implements ComputerReadStrategy {
    @Autowired
    ComputerRepository computerRepository;

    /**
     * @param pageable Busca todos os computadores dentro do banco de dados baseado em um parâmetro de paginação definido pelo controller
     * @return ComputerResponseBuilder
     */
    @Override
    public Page<ComputerResponseBuilder> selectAllPageable(Pageable pageable) {
        return computerRepository.findAll(pageable).map(ComputerResponseBuilder::new);
    }

    /**
     * @param id Busca um computador pelo ID e caso não encontre cria uma exceção personalizada
     * @return ComputerResponseBuilder
     */
    @Override
    public ComputerResponseBuilder selectById(Integer id) {
        Computer computer = computerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computador não encontrado! " + id));
        return new ComputerResponseBuilder(computer);
    }
}
