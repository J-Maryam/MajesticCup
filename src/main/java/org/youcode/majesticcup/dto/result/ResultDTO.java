package org.youcode.majesticcup.dto.result;

public record ResultDTO(
        int round,
        String team1,
        String team2,
        MatchResultDTO result,
        String winner
) {
}
