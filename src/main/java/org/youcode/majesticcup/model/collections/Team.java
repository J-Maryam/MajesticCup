package org.youcode.majesticcup.model.collections;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.youcode.majesticcup.model.sub_document.Player;

import java.util.List;

@Document(collection = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private ObjectId id;

    @NotBlank(message = "Team name cannot be blank.")
    @Indexed(unique = true)
    private String name;

    @NotBlank(message = "City name cannot be blank.")
    private String city;

    @Valid
    @Field("players")
    @NotEmpty(message = "Players list cannot be empty.")
    private List<Player> players;
}
