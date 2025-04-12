package org.kfokam48.gestiondesutilisateurs;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kfokam48.gestiondesutilisateurs.dto.UserDTO;
import org.kfokam48.gestiondesutilisateurs.model.User;
import org.kfokam48.gestiondesutilisateurs.repository.UserRepository;
import org.kfokam48.gestiondesutilisateurs.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setNom("John");
        user.setEmail("john@example.com");
        user.setPassword("password");

        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.addUser(userToUserDTO(user));

        assertEquals("John", createdUser.getNom());
    }
    public UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getNom());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        return userDTO;

    }
}
