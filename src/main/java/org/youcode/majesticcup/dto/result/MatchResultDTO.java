package org.youcode.majesticcup.dto.result;

import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record MatchResultDTO(
        @PositiveOrZero(message = "Team 1 goals must be 0 or positive.")
        int team1Goals,

        @PositiveOrZero(message = "Team 2 goals must be 0 or positive.")
        int team2Goals,
        List<StatisticDTO> statistics
) {}

