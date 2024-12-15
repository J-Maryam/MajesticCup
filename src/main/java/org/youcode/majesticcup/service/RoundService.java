package org.youcode.majesticcup.service;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.round.RoundRequestDTO;
import org.youcode.majesticcup.dto.round.RoundResponseDTO;

import java.util.List;

public interface RoundService {
    RoundResponseDTO createRound(RoundRequestDTO dto);
    List<RoundResponseDTO> getRoundsByCompetition(ObjectId competitionId);
    RoundResponseDTO getRoundById(ObjectId roundId);
    RoundResponseDTO updateRound(ObjectId roundId, RoundRequestDTO dto);
    void deleteRound(ObjectId roundId);
}
