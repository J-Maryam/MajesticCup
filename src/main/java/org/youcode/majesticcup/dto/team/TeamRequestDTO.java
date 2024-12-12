package org.youcode.majesticcup.dto.team;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.youcode.majesticcup.dto.player.PlayerRequestDTO;

import java.util.List;

public record TeamRequestDTO(
        @NotBlank(message = "Team name cannot be blank.")
        String name,

        @NotBlank(message = "City name cannot be blank.")
        String city,

        @NotEmpty(message = "Players list cannot be empty.")
        @Valid
        List<PlayerRequestDTO> players
) {
}
