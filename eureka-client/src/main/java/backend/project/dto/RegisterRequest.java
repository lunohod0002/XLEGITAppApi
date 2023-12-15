package backend.project.dto;

import backend.project.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonView(Views.BasicView.class)

public class RegisterRequest {
    private String email;

    private String password;
}
