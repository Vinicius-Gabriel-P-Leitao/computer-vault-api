package tech.vault.server.core.controller.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {
    @GetMapping("/create-user")
    public ResponseEntity<String> createNewUser() {
        return ResponseEntity.status(HttpStatus.OK).body("ola");
    }
}
