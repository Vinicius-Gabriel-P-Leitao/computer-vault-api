package tech.vault.server.core.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.vault.server.config.UserAuthenticate;
import tech.vault.server.database.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepository repoAuthenticate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repoAuthenticate.findByUserName(username).map(UserAuthenticate::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário mão encontrado"));
    }
}