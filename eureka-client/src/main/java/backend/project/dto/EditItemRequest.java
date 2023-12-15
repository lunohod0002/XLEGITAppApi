package backend.project.dto;

import backend.project.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@JsonView(Views.BasicView.class)
public class EditItemRequest {
    private int id;
    private String item_name;
    private String src_img;
    private String description;
    private String username;

}
