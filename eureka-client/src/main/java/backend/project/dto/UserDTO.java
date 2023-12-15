package backend.project.dto;

import backend.project.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonView(Views.BasicView.class)
public class UserDTO {
    private Integer id;

    @Nullable
    private String name;

    @NotBlank(message = "Username should not be empty")
    private String username;

    @NotBlank(message = "Password should not be empty")
    private String password;
    @NotBlank(message = "Email should not be empty")
    private String email;
    @NotBlank(message = "Role should not be empty")
    private String role;

    @Nullable
    private List<ItemDTO> items;
}
