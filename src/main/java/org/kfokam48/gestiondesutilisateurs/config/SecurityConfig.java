package org.kfokam48.gestiondesutilisateurs.config;

import org.kfokam48.gestiondesutilisateurs.repository.UserRepository;
import org.kfokam48.gestiondesutilisateurs.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {



    private JwtRequestFilter jwtRequestFilter;
    private CustomUserDetailsService userDetailsService;


    public SecurityConfig( JwtRequestFilter jwtRequestFilter) {


        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**", "/api/auth/login", "/v3/api-docs/**",
                                "/swagger-ui/**", "/swagger-resources/**", "/webjars/**","/api/users/add").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions().disable());
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, CustomUserDetailsService customUserDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder()) // Assurez-vous que le mot de passe est encodé
                .and()
                .build();
    }


}