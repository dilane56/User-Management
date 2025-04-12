package org.kfokam48.gestiondesutilisateurs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthRequest {

    @NotNull(message = "L'email ne doit pas être null")
    @NotBlank(message = "L'email ne dit pas être vide")
    private String email;
    @NotNull(message = "Le mot de passe ne doit pas être null")
    @NotBlank(message = "Le mot de passe ne doit pas être vide")
    private String password;

    // Getters et setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

