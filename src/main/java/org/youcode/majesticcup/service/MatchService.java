package org.youcode.majesticcup.service;

import org.youcode.majesticcup.dto.match.MatchRequestDTO;
import org.youcode.majesticcup.dto.match.MatchResponseDTO;

public interface MatchService {
    MatchResponseDTO createMatch(MatchRequestDTO dto);
}
