package org.youcode.majesticcup.model.collections;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "competitions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    private ObjectId id;

    @NotBlank(message = "The competition name cannot be blank.")
    @Size(min = 3, max = 100, message = "The competition name must be between 3 and 100 characters.")
    private String name;

    @Min(value = 2, message = "A competition must have at least 2 teams.")
    private int numberOfTeams;

    @NotEmpty(message = "The competition must have at least one team.")
    private List<Team> teams;

    private int currentRound;

    private List<Round> rounds;
}
