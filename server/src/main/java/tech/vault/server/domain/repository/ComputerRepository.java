package tech.vault.server.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.vault.server.domain.entity.Computer;


@Repository
public interface ComputerRepository extends JpaRepository<Computer, UUID> {
}
