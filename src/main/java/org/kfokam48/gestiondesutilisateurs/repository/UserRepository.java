package org.kfokam48.gestiondesutilisateurs.repository;

import org.kfokam48.gestiondesutilisateurs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
