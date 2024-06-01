package tech.vault.server.core.service;

import java.util.Map;

public interface ComputerCountStorageService {
    Map<String, Map<Integer, Long>> countComputerStorage();
}
