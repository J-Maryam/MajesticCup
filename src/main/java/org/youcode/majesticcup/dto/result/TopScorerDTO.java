package org.youcode.majesticcup.dto.result;

public record TopScorerDTO(
        String playerName,
        int goals,
        int assists,
        int yellowCards,
        int redCards
) {
}
