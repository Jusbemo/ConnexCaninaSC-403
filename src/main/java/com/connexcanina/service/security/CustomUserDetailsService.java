package com.connexcanina.service.security;

import com.connexcanina.dao.UsuarioDao;
import com.connexcanina.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        Long userId = usuario.getIdUsuario();

        usuario.setCitas(usuarioDao.findWithCitas(userId).getCitas());
        usuario.setConsultas(usuarioDao.findWithConsultas(userId).getConsultas());
        usuario.setMascotas(usuarioDao.findWithMascotas(userId).getMascotas());

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(usuario.getIdRol().getNombreRol())
        );

        return new CustomUserDetails(usuario, authorities);
    }
}
