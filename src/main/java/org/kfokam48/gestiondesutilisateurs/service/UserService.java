package org.kfokam48.gestiondesutilisateurs.service;


import org.kfokam48.gestiondesutilisateurs.dto.UserDTO;
import org.kfokam48.gestiondesutilisateurs.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public User addUser(UserDTO userDTO);
    public User updateUser(Long id,UserDTO userDTO);
    public User findUserById(long id);
    public User findUserByEmail(String email);
    public ResponseEntity<String> deleteUser(long id);
    public List<User> findAllUsers();
  //  public String authenticateUser(UserAthRequestDTO userAthRequestDTO);

}
