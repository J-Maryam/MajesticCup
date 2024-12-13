package org.youcode.majesticcup.dto.match;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.team.TeamRequestDTO;

public record MatchRequestDTO(
        @NotBlank(message = "Team 1 cannot be blank.")
        ObjectId team1,

        @NotBlank(message = "Team 2 cannot be blank.")
        ObjectId team2,

        @Positive(message = "The round number must be positive.")
        int round
) {}

