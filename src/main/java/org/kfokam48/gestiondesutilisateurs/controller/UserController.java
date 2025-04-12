package org.kfokam48.gestiondesutilisateurs.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kfokam48.gestiondesutilisateurs.dto.UserDTO;
import org.kfokam48.gestiondesutilisateurs.model.User;
import org.kfokam48.gestiondesutilisateurs.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Gestion des utilisateurs", description = "Endpoints pour la gestion des utilisateurs : création, mise à jour, suppression, et récupération des données.")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @Operation(
            summary = "Créer un utilisateur",
            description = "Ajoute un nouvel utilisateur en utilisant les informations fournies dans le corps de la requête."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès."),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou données incorrectes fournies.")
    })
    public ResponseEntity<User> createUser(
            @Parameter(description = "Les informations de l'utilisateur à créer", required = true)
            @Valid @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity.ok(userService.addUser(userDTO));
    }

    @GetMapping
    @Operation(
            summary = "Récupérer la liste de tous les utilisateurs",
            description = "Retourne une liste contenant les informations de tous les utilisateurs enregistrés."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs retournée avec succès.")
    })
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.findAllUsers());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un user a l'aide de son ID")
    public ResponseEntity<User> getUserById(Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Mettre à jour un utilisateur",
            description = "Modifie les informations d'un utilisateur spécifique à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour avec succès."),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé."),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou données incorrectes fournies.")
    })
    public ResponseEntity<User> updateUser(
            @Parameter(description = "L'identifiant unique de l'utilisateur à mettre à jour", required = true) @PathVariable Long id,
            @Parameter(description = "Les nouvelles informations de l'utilisateur", required = true) @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Supprimer un utilisateur",
            description = "Supprime un utilisateur spécifique à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Utilisateur supprimé avec succès."),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé.")
    })
    public ResponseEntity<String> deleteUser(
            @Parameter(description = "L'identifiant unique de l'utilisateur à supprimer", required = true) @PathVariable Long id
    ) {
        return userService.deleteUser(id);
    }
}

