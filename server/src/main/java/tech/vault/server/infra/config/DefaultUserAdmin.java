package tech.vault.server.infra.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.vault.server.core.dto.auth.RegisterRequestDTO;
import tech.vault.server.core.service.UserService;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.entity.values.Role;
import tech.vault.server.domain.repository.UserRepository;

@Configuration
public class DefaultUserAdmin {
    private final String user;
    private final String password;

    public DefaultUserAdmin() {
        Dotenv dotenv = Dotenv.load();
        this.user = dotenv.get("NAME_ADMIN");
        this.password = dotenv.get("PASSWORD_ADMIN");
    }

    @Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository, UserService userService, BCryptPasswordEncoder encoder) throws Exception {
        return args -> {
            if (userRepository.findByUserName(user).isEmpty()) {
                User admin = new User();

                admin.setUserName(user);
                admin.setPassword(password);
                admin.setRole(Role.ADMIN);

                RegisterRequestDTO register = new RegisterRequestDTO(admin.getUsername(), admin.getPassword(), admin.getRole());

                userService.userRegister(register);
            }
        };
    }
}
