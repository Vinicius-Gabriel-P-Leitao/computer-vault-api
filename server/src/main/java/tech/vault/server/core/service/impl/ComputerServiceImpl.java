package tech.vault.server.core.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import tech.vault.server.core.dto.computer.ComputerRequestBuilder;
import tech.vault.server.core.dto.computer.ComputerResponseBuilder;
import tech.vault.server.core.service.ComputerService;
import tech.vault.server.core.service.UserService;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.*;
import tech.vault.server.domain.repository.ComputerRepository;
import tech.vault.server.infra.exception.ExNotFound;

@Service
@Validated
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    private final ComputerRepository computerRepository;
    @Autowired
    private final UserService userService;
    public static final String COMPUTER_NOT_FOUND_MESSAGE = "Computador não encontrado! ";
    private final Logger logger = LoggerFactory.getLogger(ComputerServiceImpl.class);
    private Computer computer;

    @Override
    public Page<ComputerResponseBuilder> getAllComputers(Pageable pageable) {
        return computerRepository.findAll(pageable).map(ComputerResponseBuilder::new);
    }

    @Override
    public ComputerResponseBuilder getComputerById(Integer id) {
        computer = computerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(COMPUTER_NOT_FOUND_MESSAGE + id));
        return new ComputerResponseBuilder(computer);
    }

    @Override
    @Transactional
    public void setComputer(ComputerRequestBuilder request) {
        userService.userIsPresent(request.generalData().user());

        computer = new Computer(request);
        logger.info("Dados recebido com sucesso: {}", request);

        computerRepository.save(computer);
        logger.info("Computador inserido: {}", computer.toString());
    }

    @Override
    @Transactional
    public void patchComputer(Integer id, ComputerRequestBuilder request) {
        userService.userIsPresent(request.generalData().user());

        Computer currentComputer = computerRepository.findById(id)
                .orElseThrow(() -> new ExNotFound(COMPUTER_NOT_FOUND_MESSAGE + id));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(request);

        JsonNode generalData = jsonNode.get("dados-gerais");
        if (!generalData.isNull()) {
            JsonNode userNode = generalData.get("quem-adicionou");
            if (!userNode.isNull()) {
                currentComputer.setUser(userNode.asText());
            }
            JsonNode businessUnitNode = generalData.get("unidade-de-negocio");
            if (!businessUnitNode.isNull()) {
                currentComputer.setBusinessUnit(businessUnitNode.asText());
            }
            JsonNode departmentNode = generalData.get("departamento");
            if (!departmentNode.isNull()) {
                currentComputer.setDepartment(departmentNode.asText());
            }
            JsonNode numberPatrimonyNode = generalData.get("numero-patrimonio");
            if (!numberPatrimonyNode.isNull()) {
                currentComputer.setNumberPatrimony(numberPatrimonyNode.asText());
            }
            JsonNode locationComputerNode = generalData.get("local");
            if (!locationComputerNode.isNull()) {
                currentComputer.setLocationComputer(LocationComputer.valueOf(locationComputerNode.asText()));
            }
        }

        JsonNode hardwareNode = jsonNode.get("hardware");
        if (!hardwareNode.isNull()) {
            JsonNode computerBrandNode = hardwareNode.get("marca-computador");
            if (!computerBrandNode.isNull()) {
                currentComputer.setComputerBrand(computerBrandNode.asText());
            }
            JsonNode computerTypeNode = hardwareNode.get("tipo-computador");
            if (!computerTypeNode.isNull()) {
                currentComputer.setTypeComputer(TypeComputer.valueOf(computerTypeNode.asText()));
            }
            JsonNode nameNode = hardwareNode.get("nome");
            if (!nameNode.isNull()) {
                currentComputer.setNameComputer(nameNode.asText());
            }
            JsonNode ipNode = hardwareNode.get("ip");
            if (!ipNode.isNull()) {
                currentComputer.setIp(ipNode.asText());
            }
            JsonNode cpuNode = hardwareNode.get("processador");
            if (!cpuNode.isNull()) {
                currentComputer.setCpu(cpuNode.asText());
            }
            JsonNode ramNode = hardwareNode.get("memoria-ram");
            if (!ramNode.isNull()) {
                currentComputer.setMemoryRam(ramNode.asInt());
            }
            JsonNode ramFrequencyNode = hardwareNode.get("frequência-ram");
            if (!ramFrequencyNode.isNull()) {
                currentComputer.setFrequencyRam(ramFrequencyNode.asInt());
            }
            JsonNode ramTypeNode = hardwareNode.get("tipo-ram");
            if (!ramTypeNode.isNull()) {
                currentComputer.setTypeRam(TypeRam.valueOf(ramTypeNode.asText()));
            }
            JsonNode ramModelNode = hardwareNode.get("modelo-ram");
            if (!ramModelNode.isNull()) {
                currentComputer.setModelRam(ModelRam.valueOf(ramModelNode.asText()));
            }
            JsonNode installedRamNode = hardwareNode.get("quantidade-instalada");
            if (!installedRamNode.isNull()) {
                currentComputer.setAmountOfRamInstalled(installedRamNode.asInt());
            }
            JsonNode hdNode = hardwareNode.get("HD");
            if (!hdNode.isNull()) {
                currentComputer.setHd(hdNode.asInt());
            }
            JsonNode ssdNode = hardwareNode.get("SSD");
            if (!ssdNode.isNull()) {
                currentComputer.setSsd(ssdNode.asInt());
            }
        }

        JsonNode softwareNode = jsonNode.get("software");
        if (!softwareNode.isNull()) {
            JsonNode soNode = softwareNode.get("sistema-operacional");
            if (!soNode.isNull()) {
                currentComputer.setNameSo(So.valueOf(soNode.asText()));
            }
        }

        computerRepository.save(currentComputer);
        logger.info("Operação patch concluída com sucesso para o ID: {}", id);
    }

    @Override
    public void deleteComputer(Integer id) {
        computer = computerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(COMPUTER_NOT_FOUND_MESSAGE + id));
        logger.info("Id recebido: {}", id);

        computerRepository.delete(computer);
        logger.info("Computador deletado com sucessor: {}", computer.toString());
    }
}
