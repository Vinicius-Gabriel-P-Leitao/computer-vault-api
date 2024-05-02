package tech.vault.server.core.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.vault.server.infra.config.UserAuthenticate;
import tech.vault.server.domain.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).map(UserAuthenticate::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}