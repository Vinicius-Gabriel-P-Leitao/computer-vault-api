package tech.vault.server.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.vault.server.application.dto.auth.RegisterRequestDTO;
import tech.vault.server.application.service.security.UserService;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.entity.values.Role;
import tech.vault.server.domain.repository.UserRepository;

@Configuration
public class DefaultUserAdmin {
    @Autowired
    private Environment environment;

    @Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository, UserService userService, BCryptPasswordEncoder encoder) {
        String userName = environment.getProperty("name.admin");
        String password = environment.getProperty("password.admin");

        return args -> {
            if (userRepository.findByUserName(userName).isEmpty()) {
                User admin = new User();

                admin.setUserName(userName);
                admin.setPassword(password);
                admin.setRole(Role.ADMIN);

                RegisterRequestDTO register = new RegisterRequestDTO(admin.getUsername(), admin.getPassword(), admin.getRole());

                userService.userRegister(register);
            }
        };
    }
}
