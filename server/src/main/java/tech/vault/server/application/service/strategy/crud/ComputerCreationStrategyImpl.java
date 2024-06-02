package tech.vault.server.application.service.strategy.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.vault.server.application.dto.computer.ComputerRequestBuilder;
import tech.vault.server.application.service.security.UserService;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.repository.ComputerRepository;

@Component
@RequiredArgsConstructor
public class ComputerCreationStrategyImpl implements ComputerCreationStrategy {
    @Autowired
    UserService userService;
    @Autowired
    ComputerRepository computerRepository;

    /**
     * @param request
     * Insere dentro do banco de dados um computador baseado em um DTO
     */
    @Override
    @Transactional
    public void insertComputer(ComputerRequestBuilder request) {
        userService.userIsPresent(request.generalData().user());
        Computer computer = new Computer(request);

        computerRepository.save(computer);
    }
}
