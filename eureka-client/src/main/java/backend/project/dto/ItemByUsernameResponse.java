package backend.project.dto;

import backend.project.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@JsonView(Views.BasicView.class)
public class ItemByUsernameResponse {

    private int id;

    @Nullable
    private String description;

    @NotBlank(message = "Model should not be empty")
    private String model;

    @NotBlank(message = "Brand should not be empty")
    private String brand;
    @NotBlank(message = "status should not be empty")

    private int status;

    private String src_ing;
    private String item_name;

    private String username;
}
