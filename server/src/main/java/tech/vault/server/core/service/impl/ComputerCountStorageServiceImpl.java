package tech.vault.server.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.vault.server.core.service.ComputerCountStorageService;
import tech.vault.server.domain.repository.ComputerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ComputerCountStorageServiceImpl implements ComputerCountStorageService {
    @Autowired
    ComputerRepository repository;

    @Override
    public Map<String, Map<Integer, Long>> countComputerStorage() {
        Map<String, Map<Integer, Long>> storageCountMap = new HashMap<>();

        List<Object[]> resultSdd = repository.countSdd();
        List<Object[]> resultHd = repository.countHd();

        Map<Integer, Long> ssdValue = new HashMap<>();
        Map<Integer, Long> hdValue = new HashMap<>();

        for (Object[] response : resultSdd) {
            Integer ssd = (Integer) response[0];
            Long count = (Long) response[1];
            ssdValue.put(ssd, count);
        }

        for (Object[] response : resultHd) {
            Integer hd = (Integer) response[0];
            Long count = (Long) response[1];
            hdValue.put(hd, count);
        }

        storageCountMap.put("hd", hdValue);
        storageCountMap.put("ssd", ssdValue);

        return storageCountMap;
    }
}
