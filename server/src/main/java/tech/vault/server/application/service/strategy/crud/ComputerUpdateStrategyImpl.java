package tech.vault.server.application.service.strategy.crud;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.vault.server.application.dto.computer.ComputerRequestBuilder;
import tech.vault.server.application.service.security.UserService;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.*;
import tech.vault.server.domain.repository.ComputerRepository;
import tech.vault.server.infra.exception.ExNotFound;

@Component
@RequiredArgsConstructor
public class ComputerUpdateStrategyImpl implements ComputerUpdateStrategy {
    @Autowired
    UserService userService;
    @Autowired
    ComputerRepository computerRepository;

    /**
     * @param id
     * @param request
     * Realiza um update em um computador dentro do banco de dados baseado em seu ID e Request DTO
     */
    @Override
    @Transactional
    public void updateComputer(Integer id, ComputerRequestBuilder request) {
        userService.userIsPresent(request.generalData().user());

        Computer currentComputer = computerRepository.findById(id)
                .orElseThrow(() -> new ExNotFound("Computador não encontrado! " + id));

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
    }
}
