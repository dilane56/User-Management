package org.kfokam48.gestiondesutilisateurs.service;


import org.kfokam48.gestiondesutilisateurs.dto.UserDTO;
import org.kfokam48.gestiondesutilisateurs.exception.UserEmailAlreadyExistException;
import org.kfokam48.gestiondesutilisateurs.exception.UserModificationException;
import org.kfokam48.gestiondesutilisateurs.exception.UserNotFoundException;
import org.kfokam48.gestiondesutilisateurs.model.User;
import org.kfokam48.gestiondesutilisateurs.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   private final UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(UserDTO userDTO) {
        User user = new User();
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if(u.getEmail().equals(userDTO.getEmail())) {
                throw new UserEmailAlreadyExistException(" l' Email : "+userDTO.getEmail()+" existe déja");
            }
        }
        user.setEmail(userDTO.getEmail());
        user.setNom(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return user ;
    }

    @Override
    public User updateUser(Long id,UserDTO userDTO) {

        // Récupérer l'utilisateur authentifié
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authenticatedUsername = userDetails.getUsername();

        // Récupérer l'utilisateur à modifier
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

        // Vérifier si l'utilisateur authentifié essaie de modifier son propre compte
        if (userToUpdate.getEmail().equals(authenticatedUsername)) {
            throw new UserModificationException("Vous ne pouvez pas modifier les informations de  cet utilisateur car il est connecté.");
        }
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("L'utilisateur avec l'ID: "+id+" n'existe pas"));
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if(u.getEmail().equals(userDTO.getEmail())) {
                throw new UserEmailAlreadyExistException(" l' Email : "+userDTO.getEmail()+" existe déja");
            }
        }
            user.setNom(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRole("USER");
            userRepository.save(user);
            return user;


    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("L'utilisateur avec l'ID: "+id+" n'existe pas"));
    }

    @Override
    public User findUserByEmail(String email) {

        List<User> users = userRepository.findAll();
        for (User u : users) {
            if(u.getEmail().equals(email)) {
                throw new UserEmailAlreadyExistException(" l' Email : "+email+" existe déja");
            }
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<String> deleteUser(long id) {

        // Récupérer l'utilisateur authentifié
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String authenticatedUsername = userDetails.getUsername();

        // Récupérer l'utilisateur à modifier
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé"));

        // Vérifier si l'utilisateur authentifié essaie de modifier son propre compte
        if (userToUpdate.getEmail().equals(authenticatedUsername)) {
            throw new UserModificationException("Vous ne pouvez pas supprimer cet utilisateur car il est connecté");
        }
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("L'utilisateur avec l'ID: "+id+" n'existe pas"));
        userRepository.deleteById(id);
        return ResponseEntity.ok("Utilisateur avec l'ID: "+id+" supprimer avec succes");

    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

//    @Override
//    public String authenticateUser(UserAthRequestDTO userAthRequestDTO) {
//        User user = userRepository.findByEmail(userAthRequestDTO.getEmail());
//        if(user != null){
//            if(passwordEncoder.matches(userAthRequestDTO.getPassword(), user.getPassword())){
//                return "Authenticated";
//            }else{
//                return "Wrong password";
//            }
//        }else{
//
//            return "Invalid email ";
//
//        }
//
//    }
}
