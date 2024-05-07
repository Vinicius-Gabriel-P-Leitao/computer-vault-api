package tech.vault.server.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.vault.server.core.dto.authenticate.AuthenticationRequest;
import tech.vault.server.core.dto.authenticate.AuthenticationResponse;
import tech.vault.server.core.dto.authenticate.RegisterRequest;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.repository.UserRepository;
import tech.vault.server.infra.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse userRegister(RegisterRequest request) {
        var user = new User();
        user.setUserName(request.userName());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        userRepository.save(user);
        var jwtToken = jwtUtil.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );

        var user = userRepository.findByUserName(request.userName()).orElseThrow();
        var jwtToken = jwtUtil.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}