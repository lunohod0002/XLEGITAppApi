package backend.project.util.validator;

import backend.project.dto.RegisterRequest;
import backend.project.entity.User;
import backend.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest request = (RegisterRequest) target;
        Optional<User> existingUser = userService.getUserByEmail(request.getEmail());

        if (existingUser.get().getEmail() == request.getEmail()){
            errors.rejectValue("Email", "", "This user already exist.");
        }
    }
}
