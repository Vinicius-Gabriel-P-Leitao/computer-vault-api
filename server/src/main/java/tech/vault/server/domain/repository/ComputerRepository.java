package tech.vault.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.vault.server.domain.entity.Computer;

import java.util.List;
import java.util.UUID;


@Repository
public interface ComputerRepository extends JpaRepository<Computer, UUID> {
    @Query("SELECT c.locationComputer, COUNT(c) FROM Computer c GROUP BY c.locationComputer")
    List<Object[]> countComputersByLocation();
}
