package backend.project.service;

import backend.project.dto.ItemByUsernameResponse;
import backend.project.dto.UserDTO;
import backend.project.entity.User;

import java.util.Optional;

public interface UserService {
    UserDTO getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    UserDTO updateUser(UserDTO userDTO);
    UserDTO addItemsByUsername(ItemByUsernameResponse itemsDTO);

}
