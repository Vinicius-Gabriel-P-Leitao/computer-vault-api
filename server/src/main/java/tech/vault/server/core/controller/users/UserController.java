package tech.vault.server.core.controller.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vault.server.core.dto.authenticate.AuthenticationRequest;
import tech.vault.server.core.dto.authenticate.AuthenticationResponse;
import tech.vault.server.core.dto.authenticate.RegisterRequest;
import tech.vault.server.core.service.UserService;
import tech.vault.server.domain.entity.values.Role;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(
            @RequestHeader(name = "username") String userName,
            @RequestHeader(name = "password") String password,
            @RequestHeader(name = "role") Role role
    ) {
        RegisterRequest request = new RegisterRequest(userName, password, role);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.userRegister(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestHeader(name = "username") String userName,
                                                                   @RequestHeader(name = "password") String password) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(userName, password);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.authenticate(authenticationRequest));
    }
}
