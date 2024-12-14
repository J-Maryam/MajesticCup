package org.youcode.majesticcup.dto.competition;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.round.RoundResponseDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;

import java.util.List;

public record CompetitionRequestDTO(
        @NotBlank(message = "The competition name cannot be blank.")
        @Size(min = 3, max = 100, message = "The competition name must be between 3 and 100 characters.")
        String name,

        @Min(value = 2, message = "A competition must have at least 2 teams.")
        int numberOfTeams,

        @NotEmpty(message = "The team list cannot be empty.")
        List<TeamResponseDTO> teamIds,
        List<RoundResponseDTO> rounds

) {}

