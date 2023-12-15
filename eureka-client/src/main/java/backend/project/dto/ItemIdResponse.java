package backend.project.dto;

import backend.project.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemIdResponse {
    private int id;
}
