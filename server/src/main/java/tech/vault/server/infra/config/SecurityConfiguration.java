package tech.vault.server.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tech.vault.server.infra.filter.JwtAuthenticationFilter;

/**
 * Configuração de segurança para a aplicação utilizando Spring Security.
 *
 * <p>Esta classe configura regras de autorização e autenticação para diferentes endpoints da aplicação.
 * Utiliza {@link EnableWebSecurity} para habilitar a segurança web e define um filtro JWT para autenticação.</p>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Configura o filtro de segurança HTTP para manipular as requisições da aplicação.
     *
     * @param httpSecurity O objeto {@link HttpSecurity} usado para configurar as regras de segurança.
     * @return Um {@link SecurityFilterChain} configurado com as regras de segurança especificadas.
     * @throws Exception Se ocorrer um erro durante a configuração de segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}
