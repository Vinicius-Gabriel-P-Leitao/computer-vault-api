package tech.vault.server.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.vault.server.core.service.ComputerCountAllService;
import tech.vault.server.domain.repository.ComputerRepository;

@Service
@RequiredArgsConstructor
public class ComputerCountAllServiceImpl implements ComputerCountAllService {
    @Autowired
    ComputerRepository repository;

    @Override
    public Integer countAllComputers() {
        return repository.countAllComputers();
    }
}
