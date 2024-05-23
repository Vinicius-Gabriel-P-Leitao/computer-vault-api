package tech.vault.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.vault.server.domain.entity.Computer;
import tech.vault.server.domain.entity.values.*;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {
    @Query("SELECT c.locationComputer, COUNT(c) FROM Computer c GROUP BY c.locationComputer")
    List<Object[]> countComputersByLocation();

    @Transactional
    @Modifying
    @Query("UPDATE Computer c " +
            "SET c.user = COALESCE(:user, c.user), " +
            "c.computerCondition = COALESCE(:computerCondition, c.computerCondition), " +
            "c.businessUnit = COALESCE(:businessUnit, c.businessUnit), " +
            "c.department = COALESCE(:department, c.department), " +
            "c.numberPatrimony = COALESCE(:numberPatrimony, c.numberPatrimony), " +
            "c.locationComputer = COALESCE(:locationComputer, c.locationComputer), " +
            "c.computerBrand = COALESCE(:computerBrand, c.computerBrand), " +
            "c.typeComputer = COALESCE(:typeComputer, c.typeComputer), " +
            "c.nameComputer = COALESCE(:nameComputer, c.nameComputer), " +
            "c.ip = COALESCE(:ip, c.ip), " +
            "c.cpu = COALESCE(:cpu, c.cpu), " +
            "c.memoryRam = COALESCE(:memoryRam, c.memoryRam), " +
            "c.frequencyRam = COALESCE(:frequencyRam, c.frequencyRam), " +
            "c.typeRam = COALESCE(:typeRam, c.typeRam), " +
            "c.modelRam = COALESCE(:modelRam, c.modelRam), " +
            "c.amountOfRamInstalled = COALESCE(:amountOfRamInstalled, c.amountOfRamInstalled), " +
            "c.hd = COALESCE(:hd, c.hd), " +
            "c.ssd = COALESCE(:ssd, c.ssd), " +
            "c.nameSo = COALESCE(:nameSo, c.nameSo) " +
            "WHERE c.computerId = :computerId")
    void updateComputer(
            Integer computerId,
            String user,
            String computerCondition,
            String businessUnit,
            String department,
            String numberPatrimony,
            LocationComputer locationComputer,
            String computerBrand,
            TypeComputer typeComputer,
            String nameComputer,
            String ip,
            String cpu,
            Integer memoryRam,
            Integer frequencyRam,
            TypeRam typeRam,
            ModelRam modelRam,
            Integer amountOfRamInstalled,
            Integer hd,
            Integer ssd,
            So nameSo
    );
}