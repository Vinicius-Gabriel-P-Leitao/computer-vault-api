package tech.vault.server.application.service.strategy.count;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.vault.server.domain.repository.ComputerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ComputerCountMemoryRamServiceImpl implements ComputerCountMemoryRamService {
    @Autowired
    ComputerRepository repository;

    @Override
    public Map<Integer, Long> countComputerByMemoryRam() {
        List<Object[]> result = repository.countMemoryRam();

        Map<Integer, Long> memoryRamCountMap = new HashMap<>();
        for (Object[] response : result) {
            Integer location = (Integer) response[0];
            Long count = (Long) response[1];
            memoryRamCountMap.put(location, count);
        }

        return memoryRamCountMap;
    }
}
