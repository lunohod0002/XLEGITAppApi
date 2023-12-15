package backend.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Item {
    @Id
    @Column(name = "id", nullable = false)

    private int id;
    @NotBlank(message = "Description should not be empty")
    @Column

    private String description;
    @Column

    @NotBlank(message = "Model should not be empty")
    private String model;
    @Column

    @NotBlank(message = "Brand should not be empty")
    private String brand;
    @Column

    @NotBlank(message = "Item_name should not be empty")
    private String item_name;
    @Column
    @Nullable
    private String src_img;
    @Column


    private int status;
    /*
    0-товар не куплен
    1-товар у владельца
    1-товар потерян
    3-товар украден
     */

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "item_user_id_fkey"))
    @Nullable
    @JsonIgnore
    private User user;
}
