package com.ideasexpress.sprignboot.app.demo.Config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecutiryConfig  {


    @Bean
    //authentication
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.withUsername("brian")
                                .password(encoder.encode("123"))
                                .roles("ADMIN")
                                .build();
        UserDetails user = User.withUsername("lucho")
                                .password(encoder.encode("123"))
                                .roles("user")
                                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }

 /*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf().disable().authorizeRequests()
            .requestMatchers("/cliente/listar")


    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
