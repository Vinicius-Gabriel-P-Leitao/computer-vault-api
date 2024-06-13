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
public class ComputerCountStorageServiceImpl implements ComputerCountStorageService {
    @Autowired
    ComputerRepository repository;

    /**
     * @return Map<String, Map < Integer, Long>>
     * Busca dentro do banco a quantidade e tipos de cada unidade de armazenamento dos computadores
     */
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
            if (ssd != null && count != null) {
                ssdValue.put(ssd, count);
            }
        }

        for (Object[] response : resultHd) {
            Integer hd = (Integer) response[0];
            Long count = (Long) response[1];
            if (hd != null && count != null) {
                hdValue.put(hd, count);
            }
        }

        storageCountMap.put("hd", hdValue);
        storageCountMap.put("ssd", ssdValue);

        return storageCountMap;
    }
}
