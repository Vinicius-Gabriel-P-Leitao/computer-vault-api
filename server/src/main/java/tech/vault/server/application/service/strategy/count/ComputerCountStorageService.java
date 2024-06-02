package tech.vault.server.application.service.strategy.count;

import java.util.Map;

public interface ComputerCountStorageService {
    Map<String, Map<Integer, Long>> countComputerStorage();
}
