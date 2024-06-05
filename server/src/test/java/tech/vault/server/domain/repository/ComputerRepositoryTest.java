package tech.vault.server.domain.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ComputerRepositoryTest {
    @Autowired
    private ComputerRepository repository;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        Computer computer = new Computer();

        computer.setUser("User");
        computer.setBusinessUnit("Hospital");
        computer.setDepartment("Coleta");
        computer.setNumberPatrimony("001122");
        computer.setLocationComputer(LocationComputer.ESTOQUE);
        computer.setComputerBrand("DELL");
        computer.setTypeComputer(TypeComputer.ALL_IN_ONE);
        computer.setNameComputer("Pc-1");
        computer.setIp("192.168.1.1");
        computer.setCpu("i5-8500");
        computer.setMemoryRam(8);
        computer.setFrequencyRam(2400);
        computer.setTypeRam(TypeRam.DDR4);
        computer.setModelRam(ModelRam.SODIMM);
        computer.setAmountOfRamInstalled(2);
        computer.setHd(1000);
        computer.setSsd(256);
        computer.setNameSo(So.W10);

        this.entityManager.persist(computer);
        repository.save(computer);
    }

    @Test
    void countComputersByLocation() {
        List<Object[]> result = repository.countComputersByLocation();
        assertThat(result).hasSize(1);
        assertThat(result).extracting(value -> value[0]).contains(LocationComputer.ESTOQUE);
    }

    @Test
    void testCountMemoryRam() {
        List<Object[]> result = repository.countMemoryRam();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result).extracting(value -> value[0]).contains(8);
    }

    @Test
    void testCountAllComputers() {
        Integer total = repository.countAllComputers();
        assertThat(total).isNotEqualTo(2);
        assertThat(total).isEqualTo(1);
    }

    @Test
    void testCountSdd() {
        List<Object[]> result = repository.countSdd();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result).extracting(value -> value[0]).contains(256);
    }

    @Test
    void testCountHd() {
        List<Object[]> result = repository.countHd();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result).extracting(value -> value[0]).contains(1000);
    }
}