package tech.vault.server.application.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.vault.server.application.dto.auth.AuthenticationRequestDTO;
import tech.vault.server.application.dto.auth.AuthenticationResponseDTO;
import tech.vault.server.application.dto.auth.RegisterRequestDTO;
import tech.vault.server.domain.entity.User;
import tech.vault.server.domain.repository.UserRepository;
import tech.vault.server.infra.exception.ExNotFound;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDTO userRegister(RegisterRequestDTO request) {
        var user = new User();
        user.setUserName(request.userName());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );

        var user = userRepository.findByUserName(request.userName()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    @Override
    public User userIsPresent(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new ExNotFound("Usuário inserido não existe"));
    }
}