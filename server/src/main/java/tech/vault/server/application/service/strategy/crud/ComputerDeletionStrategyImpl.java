package tech.vault.server.application.service.strategy.crud;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.repository.ComputerRepository;

@Component
@RequiredArgsConstructor
public class ComputerDeletionStrategyImpl implements ComputerDeletionStrategy {
    @Autowired
    ComputerRepository computerRepository;

    /**
     * @param id Deleta um computador dentro do banco baseado em seu ID
     */
    @Override
    public void deleteComputer(Integer id) {
        Computer computer = computerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computador não encontrado! " + id));

        computerRepository.delete(computer);
    }
}
