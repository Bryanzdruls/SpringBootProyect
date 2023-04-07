package com.ideasexpress.sprignboot.app.demo.Config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Cliente;

public class UserInfoUserDetails implements UserDetails {

    private String usuario;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails (Cliente cliente){
        usuario=cliente.getUsuario();
        password=cliente.getPass();
        authorities=Arrays.stream(cliente.getRoles().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return usuario;
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
    
}
