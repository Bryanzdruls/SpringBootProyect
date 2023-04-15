package com.ideasexpress.sprignboot.app.demo.Config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecutiryConfig  {


    @Bean
    //authentication
    public UserDetailsService userDetailsService(){
        /*UserDetails admin = User.withUsername("brian")
                                .password(encoder.encode("123"))
                                .roles("ADMIN")
                                .build();
        UserDetails user = User.withUsername("lucho")
                                .password(encoder.encode("123"))
                                .roles("USER")
                                .build();
        return new InMemoryUserDetailsManager(admin,user);*/
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable().authorizeHttpRequests()
            .antMatchers("/producto/**", "/cliente/**","cliente/form").permitAll()
            .and()
            //.authorizeHttpRequests().antMatchers().authenticated()
            //.and()
            .formLogin()
            .and().build();


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
