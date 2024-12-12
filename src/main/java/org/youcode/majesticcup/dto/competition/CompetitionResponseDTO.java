package org.youcode.majesticcup.dto.competition;

import java.util.List;

public record CompetitionResponseDTO(
        String id,
        String name,
        int numberOfTeams,
        List<String> teams,
        int currentRound,
        List<String> rounds
) {}

