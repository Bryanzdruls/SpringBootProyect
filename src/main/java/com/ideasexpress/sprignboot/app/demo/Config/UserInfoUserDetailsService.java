package com.ideasexpress.sprignboot.app.demo.Config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;
import com.ideasexpress.sprignboot.app.demo.Models.Repository.IClienteRepo;


@Component
public class UserInfoUserDetailsService implements UserDetailsService{
    @Autowired
    private IClienteRepo repo;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> cliente=repo.findByUsuario(username);
        return cliente.map(UserInfoUserDetails::new)
            .orElseThrow(()-> new UsernameNotFoundException("user not found "+ username));
    }

}
