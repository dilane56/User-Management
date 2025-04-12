package org.kfokam48.gestiondesutilisateurs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Le nom est requis")
    @NotNull(message = "le nom ne doit pas être null")
    private String name;

    @NotBlank(message = "L'email est requis")
    @Email(message = "Email invalide")
    @NotNull(message = "email ne doit pas être null")
    private String email;

    @NotBlank(message = "Le mot de passe est requis")
    @NotNull(message = "le mot de passe ne doit pas être null")
    private String password;

    // Getters et Setters
}


