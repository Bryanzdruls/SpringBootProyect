package com.ideasexpress.sprignboot.app.demo.Config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
        UserDetails user = User.withUsername("juan")
                                .password(encoder.encode("123"))
                                .roles("USER")
                                .build();
        return new InMemoryUserDetailsManager(admin,user);*/
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf().disable().authorizeHttpRequests()
            .antMatchers("cliente/form","/h2-console/**").permitAll()
            .antMatchers("/public/**","/css/**","/js/**","/images/**").permitAll()
            .antMatchers("/login").permitAll()
            .and()
            .authorizeHttpRequests().antMatchers("/cliente/listar","/producto/**").authenticated()
            .and()
            .formLogin(login -> login
                    .loginPage("/login")
                    .permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/cliente/listar")
            .permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
            .csrf().ignoringAntMatchers("/h2-console/**")
            .and()
            .headers().frameOptions().sameOrigin()
            .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    } 


}
