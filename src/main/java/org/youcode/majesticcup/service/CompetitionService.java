package org.youcode.majesticcup.service;

import org.youcode.majesticcup.dto.competition.CompetitionRequestDTO;
import org.youcode.majesticcup.dto.competition.CompetitionResponseDTO;

public interface CompetitionService {
    CompetitionResponseDTO createCompetition(CompetitionRequestDTO dto);
}
