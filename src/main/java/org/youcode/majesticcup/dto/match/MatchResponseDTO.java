package org.youcode.majesticcup.dto.match;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.result.MatchResultDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;

public record MatchResponseDTO(
        ObjectId id,
        int round,
        TeamResponseDTO team1,
        TeamResponseDTO team2,
        MatchResultDTO result,
        TeamResponseDTO winner
) {}

