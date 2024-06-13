package tech.vault.server.application.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    /**
     * @param request Recebe os dados de usuário baseado em um DTO que pede nome, senha e role
     * @return AuthenticationResponseDTO Retorna um token para o usuário novo
     */
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public AuthenticationResponseDTO userRegister(RegisterRequestDTO request) {
        var user = new User();
        user.setUserName(request.userName());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    /**
     * @param request Pede o nome e senha do usuário autentica ele verificando se existe no banco e se as senhas condizem, após isso retorna um token para ele usando o jwtService
     * @return AuthenticationResponseDTO retorna se o usuário é valido ou não
     */
    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );

        var user = this.userIsPresent(request.userName());
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder().token(jwtToken).build();
    }

    /**
     * @param userName Verifica se o user existe dentro do banco de dados
     * @return User retorna se o usuário é existente ou não
     */
    @Override
    public User userIsPresent(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new ExNotFound("Usuário inserido não existe"));
    }
}