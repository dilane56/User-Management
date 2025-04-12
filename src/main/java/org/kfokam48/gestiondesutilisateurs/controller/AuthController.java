package org.kfokam48.gestiondesutilisateurs.controller;

import jakarta.validation.Valid;
import org.kfokam48.gestiondesutilisateurs.dto.AuthRequest;
import org.kfokam48.gestiondesutilisateurs.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
        try {
            String token = authService.authenticateUser(authRequest);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}

