package tech.vault.server.core.service;

import tech.vault.server.domain.entity.values.LocationComputer;

import java.util.Map;

public interface CountComputerLocationService {
    public Map<LocationComputer, Long> countComputerByLocation();
}
