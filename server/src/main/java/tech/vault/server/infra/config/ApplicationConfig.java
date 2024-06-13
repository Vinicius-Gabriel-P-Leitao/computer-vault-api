package tech.vault.server.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.vault.server.domain.repository.UserRepository;

/**
 * Configuração da aplicação para autenticação e segurança.
 *
 * <p>Esta classe configura o serviço de detalhes do usuário, provedor de autenticação DAO,
 * gerenciador de autenticação e codificador de senha para autenticação de usuários.</p>
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;

    /**
     * Cria um serviço de detalhes do usuário que busca usuários no banco de dados.
     *
     * @return Um {@link UserDetailsService} que busca usuários no banco de dados com base no nome de usuário.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userName -> userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }

    /**
     * Cria e configura um provedor de autenticação DAO.
     *
     * @return Um {@link AuthenticationProvider} que utiliza o serviço de detalhes do usuário e um codificador de senha.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passWordEncoder());

        return authProvider;
    }

    /**
     * Retorna o gerenciador de autenticação configurado com base na configuração de autenticação fornecida.
     *
     * @param authenticationConfiguration Configuração de autenticação fornecida.
     * @return Um {@link AuthenticationManager} configurado.
     * @throws Exception Se ocorrer um erro ao configurar o gerenciador de autenticação.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Retorna um codificador de senha {@link BCryptPasswordEncoder} para criptografar senhas de usuários.
     *
     * @return Um {@link PasswordEncoder} para codificar senhas.
     */
    @Bean
    public PasswordEncoder passWordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
