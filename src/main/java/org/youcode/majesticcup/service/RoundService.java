package org.youcode.majesticcup.service;

import org.youcode.majesticcup.dto.round.RoundRequestDTO;
import org.youcode.majesticcup.dto.round.RoundResponseDTO;

public interface RoundService {
    RoundResponseDTO createRound(RoundRequestDTO dto);
}
