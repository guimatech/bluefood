package br.com.softblue.bluefood.infrastructure.web.security;

import br.com.softblue.bluefood.domain.cliente.ClienteRepository;
import br.com.softblue.bluefood.domain.restaurante.RestauranteRepository;
import br.com.softblue.bluefood.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = clienteRepository.findByEmail(username);
        if (usuario == null) {
            usuario = restauranteRepository.findByEmail(username);

            if (usuario == null) {
                throw new UsernameNotFoundException(username);
            }
        }

        return new LoggedUser(usuario);
    }
}
