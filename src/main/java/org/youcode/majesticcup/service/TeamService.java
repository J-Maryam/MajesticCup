package org.youcode.majesticcup.service;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.team.TeamRequestDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;
import org.youcode.majesticcup.model.sub_document.Player;

import java.util.List;

public interface TeamService {
    TeamResponseDTO createTeam(TeamRequestDTO dto);
    TeamResponseDTO updateTeam(ObjectId teamId, TeamRequestDTO dto);
    void deleteTeam(ObjectId teamId);
    void addPlayerToTeam(ObjectId teamId, Player player);
    void removePlayerFromTeam(ObjectId teamId, ObjectId playerId);
    List<TeamResponseDTO> getTeams();
    TeamResponseDTO getTeamById(ObjectId teamId);
}
