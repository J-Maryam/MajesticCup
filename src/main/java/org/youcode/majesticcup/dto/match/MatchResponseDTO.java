package org.youcode.majesticcup.dto.match;

import org.youcode.majesticcup.dto.MatchResultDTO;

public record MatchResponseDTO(
        String id,
        int round,
        String team1,
        String team2,
        MatchResultDTO result,
        String winner
) {}

