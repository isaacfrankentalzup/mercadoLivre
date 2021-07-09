package com.mercadolivre.mercadolivre.seguranca;

import com.mercadolivre.mercadolivre.usuario.Usuario;
import com.mercadolivre.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceAuthentication implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Usuario> usuario =repository.findByEmail(s);

        if(usuario.isPresent()){
            return usuario.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos!");

    }
}
