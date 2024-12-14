package org.youcode.majesticcup.dto.match;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.bson.types.ObjectId;

public record MatchRequestDTO(
        @NotNull(message = "Team 1 must be specified.")
        ObjectId team1,

        @NotNull(message = "Team 2 must be specified.")
        ObjectId team2,

        @Positive(message = "The round number must be positive.")
        int round
) {}

