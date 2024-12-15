package org.youcode.majesticcup.model.collections;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "rounds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Round {
    @Id
    private ObjectId id;

    @Positive(message = "Round number must be a positive integer.")
    private int roundNumber;

    @NotNull(message = "Competition ID cannot be null.")
    private Competition competitionId;

    @NotEmpty(message = "Matches list cannot be empty.")
    private List<Match> matches;
}
