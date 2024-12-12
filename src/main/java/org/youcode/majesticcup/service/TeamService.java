package org.youcode.majesticcup.service;

import org.youcode.majesticcup.dto.team.TeamRequestDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;

public interface TeamService {
    TeamResponseDTO createTeam(TeamRequestDTO dto);
}
