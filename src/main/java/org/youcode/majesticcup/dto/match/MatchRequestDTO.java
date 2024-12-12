package org.youcode.majesticcup.dto.match;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MatchRequestDTO(
        @NotBlank(message = "Team 1 cannot be blank.")
        String team1,

        @NotBlank(message = "Team 2 cannot be blank.")
        String team2,

        @Positive(message = "The round number must be positive.")
        int round
) {}

