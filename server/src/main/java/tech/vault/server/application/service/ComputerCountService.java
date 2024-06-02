package tech.vault.server.application.service;

import tech.vault.server.domain.entity.values.LocationComputer;

import java.util.Map;

public interface ComputerCountService {
    Map<LocationComputer, Long> computerCountLocation();

    Map<Integer, Long> computerCountMemoryRam();

    Map<String, Map<Integer, Long>> computerCountStorage();

    Integer computerRecordCount();
}
