package tech.vault.server.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import tech.vault.server.database.entity.Roles;

@Repository
@EnableJpaRepositories
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String roleName);
}