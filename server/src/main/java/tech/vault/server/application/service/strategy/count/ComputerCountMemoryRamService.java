package tech.vault.server.application.service.strategy.count;

import java.util.Map;

public interface ComputerCountMemoryRamService {
    Map<Integer, Long> countComputerByMemoryRam();
}
