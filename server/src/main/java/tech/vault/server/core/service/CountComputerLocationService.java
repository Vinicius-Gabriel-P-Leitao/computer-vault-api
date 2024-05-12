package tech.vault.server.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.vault.server.domain.entity.values.LocationComputer;
import tech.vault.server.domain.repository.ComputerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CountComputerLocationService {
    @Autowired
    private ComputerRepository repository;

    public Map<LocationComputer, Long> countComputerByLocation() {
        List<Object[]> result = repository.countComputersByLocation();

        Map<LocationComputer, Long> locationCountMap = new HashMap<>();
        for (Object[] response : result) {
            LocationComputer location = (LocationComputer) response[0];
            Long count = (Long) response[1];
            locationCountMap.put(location, count);
        }

        return locationCountMap;
    }
}
