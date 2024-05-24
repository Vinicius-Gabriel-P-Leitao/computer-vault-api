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
}