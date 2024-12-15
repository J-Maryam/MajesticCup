package org.youcode.majesticcup.service;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.competition.CompetitionRequestDTO;
import org.youcode.majesticcup.dto.competition.CompetitionResponseDTO;

import java.util.List;

public interface CompetitionService {
    CompetitionResponseDTO createCompetition(CompetitionRequestDTO dto);
    CompetitionResponseDTO getCompetitionById(ObjectId id);
    void deleteCompetition(ObjectId id);
    List<CompetitionResponseDTO> getAllCompetitions();

}
