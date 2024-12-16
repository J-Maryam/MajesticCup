package org.youcode.majesticcup.dto.result;

public record StatisticResponseDTO(
        String playerName,
        int goals,
        int assists,
        int yellowCards,
        int redCards
) {
}
