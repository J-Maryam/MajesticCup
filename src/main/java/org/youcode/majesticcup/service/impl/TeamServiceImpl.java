package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.common.exceptions.EntityNotFoundException;
import org.youcode.majesticcup.dto.team.TeamRequestDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;
import org.youcode.majesticcup.mapper.TeamMapper;
import org.youcode.majesticcup.model.collections.Team;
import org.youcode.majesticcup.model.sub_document.Player;
import org.youcode.majesticcup.repository.TeamRepository;
import org.youcode.majesticcup.service.TeamService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;
    private final TeamMapper mapper;

    @Override
    public TeamResponseDTO createTeam(TeamRequestDTO dto) {
        Team team = buildTeamFromDTO(dto);
        Team savedTeam = repository.save(team);
        return mapper.toDto(savedTeam);
    }

    @Override
    public TeamResponseDTO updateTeam(ObjectId teamId, TeamRequestDTO dto) {
        Team existingTeam = repository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId));
        existingTeam.setName(dto.name());
        existingTeam.setCity(dto.city());
        existingTeam.setPlayers(dto.players()
                .stream()
                .map(player -> new Player(
                        player.name(),
                        player.surname(),
                        player.position(),
                        player.number()
                ))
                .toList());
        Team updatedTeam = repository.save(existingTeam);
        return mapper.toDto(updatedTeam);
    }

    @Override
    public void deleteTeam(ObjectId teamId) {
        Team existingTeam = repository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId));
        repository.delete(existingTeam);
    }

    @Override
    public void addPlayerToTeam(ObjectId teamId, Player player) {
        Team existingTeam = repository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId));
        existingTeam.getPlayers().add(player);
        repository.save(existingTeam);
    }

    @Override
    public void removePlayerFromTeam(ObjectId teamId, ObjectId playerId) {
        Team existingTeam = repository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId));
        existingTeam.setPlayers(existingTeam.getPlayers()
                .stream()
                .filter(player -> !player.getId().equals(playerId))
                .toList());
        repository.save(existingTeam);
    }

    public List<TeamResponseDTO> getTeams() {
        List<Team> teams = repository.findAll();
        return teams.stream()
                .map(mapper::toDto)
                .toList();
    }

    private Team buildTeamFromDTO(TeamRequestDTO dto) {
        List<Player> players = dto.players()
                .stream()
                .map(player -> new Player(
                        player.name(),
                        player.surname(),
                        player.position(),
                        player.number()
                ))
                .toList();
        Team team = mapper.toEntity(dto);
        team.setName(dto.name());
        team.setCity(dto.city());
        team.setPlayers(players);
        return team;
    }
}
