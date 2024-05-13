package tech.vault.server.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vault.server.core.dto.auth.AuthenticationRequest;
import tech.vault.server.core.dto.auth.AuthenticationResponse;
import tech.vault.server.core.dto.auth.RegisterRequest;
import tech.vault.server.core.service.UserService;
import tech.vault.server.domain.entity.values.Role;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestHeader(name = "username") String userName,
            @RequestHeader(name = "password") String password,
            @RequestHeader(name = "role") Role role
    ) {
        RegisterRequest request = new RegisterRequest(userName, password, role);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.userRegister(request));
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*") //TODO: Trocar por ip do front-end
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestHeader(name = "username") String userName,
                                                                   @RequestHeader(name = "password") String password) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(userName, password);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.authenticate(authenticationRequest));
    }
}
