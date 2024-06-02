package tech.vault.server.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.vault.server.application.service.strategy.count.ComputerCountLocationService;
import tech.vault.server.application.service.strategy.count.ComputerCountMemoryRamService;
import tech.vault.server.application.service.strategy.count.ComputerCountStorageService;
import tech.vault.server.application.service.strategy.count.ComputerRecordCountService;
import tech.vault.server.domain.entity.values.LocationComputer;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ComputerCountServiceImpl implements ComputerCountService {
    private final ComputerRecordCountService computerRecordCountService;
    private final ComputerCountStorageService computerCountStorageService;
    private final ComputerCountMemoryRamService computerCountMemoryRamService;
    private final ComputerCountLocationService computerCountLocationService;

    /**
     * @return Map<LocationComputer, Long>
     * Busca dentro do banco os locais e a quantidade de computadores em cada local
     */
    @Override
    public Map<LocationComputer, Long> computerCountLocation() {
        return computerCountLocationService.countComputerByLocation();
    }

    /**
     * @return Map<Integer, Long>
     * Busca dentro do banco tipos e quantidades de memórias ram presentes
     */
    @Override
    public Map<Integer, Long> computerCountMemoryRam() {
        return computerCountMemoryRamService.countComputerByMemoryRam();
    }

    /**
     * @return Map<String, Map < Integer, Long>>
     * Busca dentro do banco a quantidade e tipos de cada unidade de armazenamento dos computadores
     */
    @Override
    public Map<String, Map<Integer, Long>> computerCountStorage() {
        return computerCountStorageService.countComputerStorage();
    }

    /**
     * @return Integer
     * Busca quantos registros tem dentro da tabela de computer
     */
    @Override
    public Integer computerRecordCount() {
        return computerRecordCountService.countComputers();
    }
}
