package org.youcode.majesticcup.model.collections;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.youcode.majesticcup.model.sub_document.MatchResult;

@Document(collection = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    private ObjectId id;

    @Positive(message = "The round number must be positive.")
    private int round;

    @NotNull(message = "Team 1 must be specified.")
    private ObjectId team1;

    @NotNull(message = "Team 2 must be specified.")
    private ObjectId team2;

    private MatchResult result;

    private ObjectId winner;
}
