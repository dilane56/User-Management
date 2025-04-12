package org.kfokam48.gestiondesutilisateurs.config;
import org.kfokam48.gestiondesutilisateurs.model.User;
import org.kfokam48.gestiondesutilisateurs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultUserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifiez si l'utilisateur par défaut existe déjà
        if (userRepository.findByEmail("admin") == null) {
            User user = new User();
            user.setNom("admin");
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("password")); // Encodez le mot de passe
            user.setRole("ADMIN"); // Définissez le rôle

            userRepository.save(user); // Enregistrez l'utilisateur
        }
    }
}

