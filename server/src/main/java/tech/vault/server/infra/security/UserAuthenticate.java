package tech.vault.server.infra.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.vault.server.domain.entity.User;

import java.util.Collection;
import java.util.List;

public class UserAuthenticate implements UserDetails {
    private final User user;

    public UserAuthenticate(User user) {
        this.user = user;
    }

    @Override // NOTE: Responsável pelos papeis no usuário
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }
}
