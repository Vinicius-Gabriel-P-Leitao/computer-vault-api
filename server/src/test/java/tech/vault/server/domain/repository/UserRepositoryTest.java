package tech.vault.server.domain.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.entity.values.Role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Usuário achado com sucesso dentro do banco de dados")
    void findByUserNameSuccess() {
        String userName = "User";
        String password = "User";
        Role role = Role.USER;

        this.createUser(userName, password, role);
        Optional<User> foundUser = this.repository.findByUserName(userName);

        assertThat(foundUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Usuário não encontrado dentro do banco de dados")
    void findByUserNameError() {
        String userName = "User";

        Optional<User> foundUser = this.repository.findByUserName(userName);

        assertThat(foundUser.isEmpty()).isTrue();
    }

    //INFO: Método auxiliar
    private void createUser(String userName, String password, Role roles) {
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setPassword(password);
        newUser.setRole(roles);

        this.entityManager.persist(newUser);

    }
}