package tech.vault.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.entity.values.Role;
import tech.vault.server.domain.repository.UserRepository;

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository) throws Exception {
        return (String[] args) -> {
            User userAdm = new User();
            userAdm.setUserName("user");
            userAdm.setPassword("$2a$12$JDyYQR2w96axL2PgjPWf0eWnEdFB/MXrrgrL2vvdrsLqCAE7hLEMS");
            userAdm.setRole(Role.ADMIN);

            userRepository.save(userAdm);
            userRepository.findAll().forEach(System.out::println);
        };
    }
}