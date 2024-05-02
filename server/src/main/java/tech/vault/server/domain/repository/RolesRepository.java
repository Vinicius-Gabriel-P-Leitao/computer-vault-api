package tech.vault.server.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import tech.vault.server.domain.entity.Roles;

@Repository
@EnableJpaRepositories
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String roleName);
}