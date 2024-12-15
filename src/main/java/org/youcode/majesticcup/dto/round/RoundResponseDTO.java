package org.youcode.majesticcup.dto.round;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.competition.CompetitionResponseDTO;
import org.youcode.majesticcup.dto.match.MatchResponseDTO;

import java.util.List;

public record RoundResponseDTO(
        ObjectId id,
        int roundNumber,
        CompetitionResponseDTO competitionId,
        List<MatchResponseDTO> matches
) {}

