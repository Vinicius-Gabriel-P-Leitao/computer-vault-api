package tech.vault.server.application.service.strategy.count;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.vault.server.domain.repository.ComputerRepository;

@Component
@RequiredArgsConstructor
public class ComputerRecordCountServiceImpl implements ComputerRecordCountService {
    @Autowired
    ComputerRepository repository;

    @Override
    public Integer countComputers() {
        return repository.countAllComputers();
    }
}
