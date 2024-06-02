package tech.vault.server.application.service.strategy.count;

import tech.vault.server.domain.entity.values.LocationComputer;

import java.util.Map;

public interface ComputerCountLocationService {
     Map<LocationComputer, Long> countComputerByLocation();
}
