package backend.project.controller;

import backend.project.dto.*;
import backend.project.exception.UserNotFoundException;
import backend.project.service.ItemService;
import backend.project.service.UserService;
import backend.project.util.ErrorBuilder;
import backend.project.util.validator.UserValidator;
import backend.project.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/get_user")
    @JsonView(Views.BasicView.class)
    public ResponseEntity<UserDTO> getUserByUsername(@RequestBody UsernameRequest idRequest)
            throws UserNotFoundException {
        String username = idRequest.getUsername();

        return ResponseEntity.ok(userService.getUserByUsername(username));
    }


    @PutMapping("/change_user")
    @JsonView(Views.BasicView.class)
    public ResponseEntity<UserDTO> updateUser(
            @RequestBody @Valid UserDTO userDto) {

        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @PostMapping("/user_add_items")
    @JsonView(Views.BasicView.class)
    public ResponseEntity<UserDTO> addItemToUser(
            @RequestBody @Valid ItemByUsernameResponse itemsDto) {

        return ResponseEntity.ok(userService.addItemsByUsername(itemsDto));
    }
}
