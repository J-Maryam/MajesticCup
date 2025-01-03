package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.common.exceptions.business.EntityNotFoundException;
import org.youcode.majesticcup.dto.player.PlayerRequestDTO;
import org.youcode.majesticcup.dto.team.TeamRequestDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;
import org.youcode.majesticcup.mapper.TeamMapper;
import org.youcode.majesticcup.model.collections.Team;
import org.youcode.majesticcup.model.sub_document.Player;
import org.youcode.majesticcup.repository.TeamRepository;
import org.youcode.majesticcup.service.TeamService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;
    private final TeamMapper mapper;

    @Override
    public TeamResponseDTO createTeam(TeamRequestDTO dto) {
        validateUniquePlayers(dto.players());
        validateUniqueTeamName(dto.name());

        Team team = buildTeamFromDTO(dto);
        Team savedTeam = repository.save(team);
        return mapper.toDto(savedTeam);
    }

    @Override
    public TeamResponseDTO updateTeam(ObjectId teamId, TeamRequestDTO dto) {
        Team existingTeam = repository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId));

        Map<ObjectId, Player> existingPlayersMap = existingTeam.getPlayers()
                .stream()
                .collect(Collectors.toMap(Player::getId, Function.identity()));

        List<Player> updatedPlayers = dto.players().stream()
                .map(playerDto -> {
                    Player existingPlayer = existingPlayersMap.get(playerDto.id());

                    if (existingPlayer == null) {
                        throw new EntityNotFoundException("Player not found with ID: " + playerDto.id());
                    }

                    existingPlayer.setName(playerDto.name());
                    existingPlayer.setSurname(playerDto.surname());
                    existingPlayer.setPosition(playerDto.position());
                    existingPlayer.setNumber(playerDto.number());

                    return existingPlayer;
                })
                .collect(Collectors.toList());

        existingTeam.setPlayers(updatedPlayers);

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

    @Override
    public List<TeamResponseDTO> getTeams() {
        List<Team> teams = repository.findAll();
        return teams.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public TeamResponseDTO getTeamById(ObjectId teamId) {
        Team team = repository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId));
        return mapper.toDto(team);
    }

    private Team buildTeamFromDTO(TeamRequestDTO dto) {
        List<Player> players = dto.players()
                .stream()
                .map(player -> {
                    Player newPlayer = new Player(
                            player.name(),
                            player.surname(),
                            player.position(),
                            player.number()
                    );
                    newPlayer.setId(new ObjectId());
                    return newPlayer;
                })
                .toList();
        Team team = mapper.toEntity(dto);
        team.setName(dto.name());
        team.setCity(dto.city());
        team.setPlayers(players);
        return team;
    }

    private void validateUniqueTeamName(String teamName) {
        if (repository.existsByName(teamName)) {
            throw new IllegalArgumentException("Team name must be unique: " + teamName);
        }
    }

    private void validateUniquePlayers(List<PlayerRequestDTO> players) {
        Set<Integer> playerNumbers = new HashSet<>();

        players.stream()
                .map(PlayerRequestDTO::number)
                .filter(number -> !playerNumbers.add(number))
                .findFirst()
                .ifPresent(duplicate -> {
                    throw new IllegalArgumentException(
                            "Duplicate player number detected: " + duplicate
                    );
                });
    }


}
