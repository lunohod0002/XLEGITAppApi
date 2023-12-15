package backend.project.dto;

import backend.project.view.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@JsonView(Views.BasicView.class)
public class ItemDTO {

    private int id;

    @NotBlank(message = "Description should not be empty")
    private String description;

    @NotBlank(message = "Model should not be empty")
    private String model;

    @NotBlank(message = "Brand should not be empty")
    private String brand;
    @NotBlank(message = "Item should not be empty")
    @Nullable
    private int status;
    private String item_name;
    private String image_url;



    @Nullable
    @JsonProperty("user_id")
    @JsonView(Views.ItemView.class)
    private Integer userId;
}
