package tech.vault.server.core.service.implement;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.vault.server.core.dto.ComputerRequestBuilder;
import tech.vault.server.core.dto.ComputerResponseBuilder;
import tech.vault.server.core.service.ComputerCrudService;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.repository.ComputerRepository;
import tech.vault.server.infra.util.ArraysForOneArray;
import tech.vault.server.infra.util.GetNullProperty;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComputerCrudServiceImpl implements ComputerCrudService {
    @Autowired
    private final ComputerRepository repository;
    private Computer computer;

    @Override
    public List<ComputerResponseBuilder> getAllComputers() {
        return repository.findAll().stream().map(ComputerResponseBuilder::new).toList();
    }

    @Override
    public ComputerResponseBuilder getComputerById(UUID id) {
        computer = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computador não encontrado! " + id));
        return new ComputerResponseBuilder(computer);
    }

    @Override
    public void setComputer(ComputerRequestBuilder request) {
        computer = new Computer(request);

        repository.save(computer);
    }

    @Override
    public void patchComputer(UUID id, ComputerRequestBuilder request) {
        computer = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computador não encontrado! " + id));

        String[] nullPropertiesGeneralData = GetNullProperty.getNullProperties(request.generalData());
        String[] nullPropertiesHardware = GetNullProperty.getNullProperties(request.hardware());
        String[] nullPropertiesSoftware = GetNullProperty.getNullProperties(request.software());

        String[] nullProperties = ArraysForOneArray.combineArrays(nullPropertiesGeneralData, nullPropertiesHardware, nullPropertiesSoftware);

        BeanUtils.copyProperties(request, computer, nullProperties);
        repository.saveAndFlush(computer);
    }

    @Override
    public void deleteComputer(UUID id) {
        computer = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computador não encontrado! " + id));
        repository.delete(computer);
    }
}
