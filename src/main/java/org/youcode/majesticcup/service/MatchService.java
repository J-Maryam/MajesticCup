package org.youcode.majesticcup.service;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.MatchResultDTO;
import org.youcode.majesticcup.dto.match.MatchRequestDTO;
import org.youcode.majesticcup.dto.match.MatchResponseDTO;

public interface MatchService {
    MatchResponseDTO createMatch(MatchRequestDTO dto);
    MatchResponseDTO registerMatchResult(ObjectId matchId, MatchResultDTO matchResultDTO);
}
