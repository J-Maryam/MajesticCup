package org.youcode.majesticcup.dto.competition;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.round.RoundResponseDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;

import java.util.List;

public record CompetitionResponseDTO(
        ObjectId id,
        String name,
        int numberOfTeams,
        List<TeamResponseDTO> teams,
        int currentRound,
        List<RoundResponseDTO> rounds
) {}

