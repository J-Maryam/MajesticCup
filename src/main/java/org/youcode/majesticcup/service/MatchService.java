package org.youcode.majesticcup.service;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.result.MatchResultDTO;
import org.youcode.majesticcup.dto.match.MatchRequestDTO;
import org.youcode.majesticcup.dto.match.MatchResponseDTO;
import org.youcode.majesticcup.dto.result.ResultDTO;
import org.youcode.majesticcup.dto.result.StatisticResponseDTO;

import java.util.List;

public interface MatchService {
    MatchResponseDTO createMatch(MatchRequestDTO dto);
    MatchResponseDTO registerMatchResult(ObjectId matchId, MatchResultDTO matchResultDTO);
    List<ResultDTO> getAllMatchResults();
    List<StatisticResponseDTO> getTopScorers();
    List<StatisticResponseDTO> getTopAssists();
    List<StatisticResponseDTO> getTopCards();

}
