package org.youcode.majesticcup.dto.round;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.match.MatchRequestDTO;

import java.util.List;

public record RoundRequestDTO(
        @Positive(message = "Round number must be a positive integer.")
        int roundNumber,

        @NotNull(message = "Competition ID cannot be blank.")
        ObjectId competitionId,

        @NotEmpty(message = "Matches list cannot be empty.")
        List<ObjectId> matches
) {}
