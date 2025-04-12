package org.kfokam48.gestiondesutilisateurs.service;

import org.kfokam48.gestiondesutilisateurs.model.CustomUserDetails;
import org.kfokam48.gestiondesutilisateurs.model.User;
import org.kfokam48.gestiondesutilisateurs.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable : " + username);
        }
        return new CustomUserDetails(user.getEmail(), user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        // Ajout automatique du préfixe "ROLE_" pour la sécurité Spring.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }
}