package com.ideasexpress.sprignboot.app.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Usuario;
import com.ideasexpress.sprignboot.app.demo.Models.Repository.IUsuarioRepo;

public class UsuarioService implements UserDetailsService  {
    @Autowired
    private IUsuarioRepo usuarioRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("brian"));
        Usuario user= usuarioRepo.findByNombre(username);
        UserDetails userDet= new User(user.getNombre(),user.getClave(),roles);
        
        return userDet;
    }
    
}
