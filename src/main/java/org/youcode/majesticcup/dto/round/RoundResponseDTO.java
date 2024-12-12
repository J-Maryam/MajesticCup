package org.youcode.majesticcup.dto.round;

import org.youcode.majesticcup.dto.match.MatchResponseDTO;

import java.util.List;

public record RoundResponseDTO(
        String id,
        int roundNumber,
        String competitionId,
        List<MatchResponseDTO> matches
) {}

