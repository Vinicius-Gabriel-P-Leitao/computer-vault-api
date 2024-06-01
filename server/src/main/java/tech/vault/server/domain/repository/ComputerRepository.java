package tech.vault.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.vault.server.domain.entity.Computer;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {
    @Query("select tb.locationComputer valores, count(tb) quantidade from Computer tb group by tb.locationComputer")
    List<Object[]> countComputersByLocation();

    @Query("select tb.memoryRam valores, count(tb.memoryRam) quantidade from Computer tb group by tb.memoryRam")
    List<Object[]> countMemoryRam();

    @Query("select count(*) total_registros from Computer tb")
    Integer countAllComputers();

    @Query("select tb.ssd valores, count(*) quantidade from Computer tb group by tb.ssd")
    List<Object[]> countSdd();

    @Query("select tb.hd valores, count(*) quantidade from Computer tb group by tb.hd")
    List<Object[]> countHd();
}