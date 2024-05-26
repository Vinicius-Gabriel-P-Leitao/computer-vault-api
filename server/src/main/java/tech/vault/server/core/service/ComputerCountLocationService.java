package tech.vault.server.core.service;

import tech.vault.server.domain.entity.values.LocationComputer;

import java.util.Map;

public interface ComputerCountLocationService {
    public Map<LocationComputer, Long> countComputerByLocation();
}
