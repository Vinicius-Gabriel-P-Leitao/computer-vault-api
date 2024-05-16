package tech.vault.server.core.service.implement;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.vault.server.core.dto.ComputerRequestBuilder;
import tech.vault.server.core.dto.ComputerResponseBuilder;
import tech.vault.server.core.service.ComputerCrudService;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.repository.ComputerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerCrudServiceImpl implements ComputerCrudService {
    @Autowired
    private final ComputerRepository repository;
    private final Logger logger = LoggerFactory.getLogger(ComputerCrudServiceImpl.class);
    private Computer computer;

    @Override
    public List<ComputerResponseBuilder> getAllComputers() {
        return repository.findAll().stream().map(ComputerResponseBuilder::new).toList();
    }

    @Override
    public ComputerResponseBuilder getComputerById(Integer id) {
        computer = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computador não encontrado! " + id));
        return new ComputerResponseBuilder(computer);
    }

    @Override
    @Transactional
    public void setComputer(ComputerRequestBuilder request) {
        computer = new Computer(request);
        logger.info("Dados recebido com sucesso: {}", request);

        repository.save(computer);
        logger.info("Computador inserido: {}", computer.toString());
    }

    @Override
    @Transactional
    public void patchComputer(Integer id, ComputerRequestBuilder request) {
        repository.updateComputer(id,
                request.generalData().user(),
                request.generalData().computerCondition(),
                request.generalData().businessUnit(),
                request.generalData().department(),
                request.generalData().numberPatrimony(),
                request.generalData().locationComputer(),
                request.hardware().computerBrand(),
                request.hardware().typeComputer(),
                request.hardware().nameComputer(),
                request.hardware().ip(),
                request.hardware().cpu(),
                request.hardware().memoryRam(),
                request.hardware().frequencyRam(),
                request.hardware().typeRam(),
                request.hardware().modelRam(),
                request.hardware().amountOfRamInstalled(),
                request.hardware().hd(),
                request.hardware().ssd(),
                request.software().so()
        );
        logger.info("Operação patch concluída com sucesso para o ID: {}", id);
    }

    @Override
    public void deleteComputer(Integer id) {
        computer = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Computador não encontrado! " + id));
        logger.info("Id recebido: {}", id);

        repository.delete(computer);
        logger.info("Computador deletado com sucessor: {}", computer.toString());
    }
}
