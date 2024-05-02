package tech.vault.server.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.vault.server.database.entity.User;
import tech.vault.server.database.entity.values.Computer;

import java.util.UUID;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, UUID> {
}
