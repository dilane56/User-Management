# Gestion des Utilisateurs

## Description

Ce projet est une application de gestion des utilisateurs développée avec Spring Boot. Il permet de créer, lire, mettre à jour et supprimer des utilisateurs tout en assurant la sécurité via l'authentification JWT.

## Fonctionnalités

- **Création d'utilisateurs** : Ajoutez de nouveaux utilisateurs via une API REST.
- **Récupération des utilisateurs** : Obtenez la liste de tous les utilisateurs ou un utilisateur spécifique par son ID.
- **Mise à jour des utilisateurs** : Modifiez les informations d'un utilisateur existant.
- **Suppression des utilisateurs** : Supprimez un utilisateur de la base de données.
- **Sécurité** : Protection des endpoints avec authentification JWT.

## Technologies Utilisées

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **JPA / Hibernate**
- **postgreSQL**
- **Swagger** pour la documentation des API
- **Maven** pour la gestion des dépendances

## Installation

1. Clonez le repository :
   ```bash
   git clone https://github.com/votre-utilisateur/gestion-des-utilisateurs.git
   cd gestion-des-utilisateurs
2. Configurez votre base de données postgres dans le fichier application.properties :

```
spring.datasource.url=jdbc:jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe

```
3. Exécutez l'application :
```bash
mvn spring-boot:run
```
### Utilisation

- **Authentification**
Pour accéder aux endpoints protégés, vous devez d'abord vous authentifier et obtenir un token JWT. Utilisez l'endpoint suivant :
```
POST /api/auth/login
```
Corps de la requête :
```json
{
  "email": "votre email",
  "password": "votre_mot_de_passe"
}
```
### Endpoints

1. Créer un utilisateur

  `POST /api/users/add`
Corps de la requête :
```json
{
  "username": "nouvel_utilisateur",
  "email": "votre email"
  "password": "mot_de_passe",
  "role": "USER"
}
```
2. Récupérer tous les utilisateurs

  `GET /api/users`

3. Récupérer un utilisateur par ID

  `GET /api/users/{id}`

4. Mettre à jour un utilisateur

  `PUT /api/users/update/{id}`
Corps de la requête :
```json
{
  "username": "utilisateur_modifié",
  "email": "new email",
  "password": "nouveau_mot_de_passe",
  
}
```
5. Supprimer un utilisateur

  `DELETE /api/users/{id}`

### Gestion des Erreurs
Les erreurs sont gérées via des exceptions personnalisées. En cas d'erreur lors de la modification ou de la suppression de votre propre compte, une réponse avec le statut HTTP 403 (Forbidden) sera renvoyée.

### Contribuer
Les contributions sont les bienvenues ! Veuillez soumettre un pull request pour toute amélioration ou correction.


### Remarques

- Assurez-vous de remplacer les placeholders comme `votre_utilisateur`, `votre_base_de_donnees`, etc., par les valeurs appropriées pour votre projet.
- Vous pouvez également ajouter des sections supplémentaires selon les besoins, comme des informations sur les tests, le déploiement, etc.
