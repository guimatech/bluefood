package br.com.softblue.bluefood.infrastructure.web.security;

import br.com.softblue.bluefood.domain.cliente.Cliente;
import br.com.softblue.bluefood.domain.restaurante.Restaurante;
import br.com.softblue.bluefood.domain.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class LoggedUser implements UserDetails {

    private Usuario usuario;
    private Role role;
    private Collection<? extends GrantedAuthority> roles;

    public LoggedUser(Usuario usuario) {
        this.usuario = usuario;

        Role role;

        if (usuario instanceof Cliente) {
            role = Role.CLIENTE;
        } else if (usuario instanceof Restaurante) {
            role = Role.RESTAURANTE;
        } else {
            throw new IllegalStateException("O tipo de usuário não é válido.");
        }

        this.role = role;
        this.roles = List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role getRole() {
        return role;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
