package org.kfokam48.gestiondesutilisateurs.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.kfokam48.gestiondesutilisateurs.dto.AuthRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    // Clé secrète pour signer les tokens
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public AuthService( AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {

        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    // Retourne la clé secrète
    public static SecretKey getSecretKey() {
        return secretKey;
    }

    /**
     * Authentifie un utilisateur et génère un token JWT.
     *
     * @param authRequest Les détails d'authentification de l'utilisateur.
     * @return Le token JWT généré.
     */
    public String authenticateUser(@Valid AuthRequest authRequest) {
        try {
            // Authentification
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            // Récupération des détails de l'utilisateur
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());

            // Génération du token JWT
            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expire dans 1 jour
                    .signWith(secretKey)
                    .compact();

        } catch (Exception e) {
            // Gestion des erreurs avec un message explicite
            throw new RuntimeException("Identifiants invalides : vérifiez l'e-mail ou le mot de passe.", e);
        }
    }
}