package backend.project.service.implementation;

import backend.project.dto.ItemByUsernameResponse;
import backend.project.dto.ItemDTO;
import backend.project.dto.UserDTO;
import backend.project.entity.Item;
import backend.project.entity.User;
import backend.project.exception.UserNotFoundException;
import backend.project.repository.UserRepository;
import backend.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ItemServiceImpl itemService;
    @Override
    public UserDTO getUserByUsername(String username) {
        var optionalUser = userRepository.findUserByUsername(username);
        var exisitinguser = optionalUser.orElseThrow(
                () -> new UserNotFoundException("User " + username + " does not exist"));
        return  modelMapper.map(exisitinguser, UserDTO.class);
    }


    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        var user = modelMapper.map(userDTO, User.class);
        var userOptional = userRepository.findUserByUsername(userDTO.getUsername());
        var existingUser = userOptional.orElseThrow(() ->
                new UserNotFoundException("User " + " not found"));

        existingUser.setName(user.getName());
        existingUser.setUsername(user.getUsername());
        existingUser.getItems().clear();

        for (Item item : user.getItems()) {
            item.setUser(existingUser);
            existingUser.getItems().add(item);
        }

        userRepository.save(existingUser);
        return modelMapper.map(existingUser, UserDTO.class);
    }

    @Override
    public UserDTO addItemsByUsername(ItemByUsernameResponse itemsDTO) {
        ItemDTO itemDTO=new ItemDTO();
        itemDTO.setId(itemDTO.getId());
        itemDTO.setBrand(itemDTO.getBrand());
        itemDTO.setModel(itemDTO.getModel());
        itemDTO.setDescription(itemDTO.getDescription());
        itemDTO.setStatus(itemDTO.getStatus());


        var item = modelMapper.map(itemDTO,Item.class);

        var userOptional = userRepository.findUserByUsername(itemsDTO.getUsername());
        var user = userOptional.orElseThrow(() ->
                new UserNotFoundException("ОК"));

        user.getItems().add(item);
        item.setUser(user);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);

    }


}
