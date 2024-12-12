package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        Team savedTeam = repository.save(team);
        return mapper.toDto(savedTeam);
    }
}
