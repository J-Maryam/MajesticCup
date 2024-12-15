package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.common.exceptions.EntityNotFoundException;
import org.youcode.majesticcup.dto.result.MatchResultDTO;
import org.youcode.majesticcup.dto.result.StatisticDTO;
import org.youcode.majesticcup.dto.match.MatchRequestDTO;
import org.youcode.majesticcup.dto.match.MatchResponseDTO;
import org.youcode.majesticcup.dto.result.ResultDTO;
import org.youcode.majesticcup.mapper.MatchMapper;
import org.youcode.majesticcup.model.collections.Match;
import org.youcode.majesticcup.model.sub_document.MatchResult;
import org.youcode.majesticcup.model.sub_document.Player;
import org.youcode.majesticcup.model.sub_document.Statistic;
import org.youcode.majesticcup.repository.MatchRepository;
import org.youcode.majesticcup.repository.TeamRepository;
import org.youcode.majesticcup.service.MatchService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchRepository repository;
    private final MatchMapper mapper;
    private final TeamRepository teamRepository;

    @Override
    public MatchResponseDTO createMatch(MatchRequestDTO dto) {
        if (dto.team1().equals(dto.team2())) {
            throw new IllegalArgumentException("Team 1 and Team 2 cannot be the same.");
        }

        Match match = mapper.toEntity(dto);
        match.setRound(dto.round());

        match.setTeam1(teamRepository.findById(dto.team1())
                .orElseThrow(() -> new EntityNotFoundException("Team 1 not found")));
        match.setTeam2(teamRepository.findById(dto.team2())
                .orElseThrow(() -> new EntityNotFoundException("Team 2 not found")));

        Match savedMatch = repository.save(match);
        return mapper.toDto(savedMatch);
    }

    @Override
    public MatchResponseDTO registerMatchResult(ObjectId matchId, MatchResultDTO matchResultDTO) {
        Match match = repository.findById(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Match not found with id: " + matchId));

        if (match.getResult() != null) {
            throw new IllegalStateException("Match result has already been recorded.");
        }

        List<ObjectId> validPlayerIds = Stream.concat(
                match.getTeam1().getPlayers().stream().map(Player::getId),
                match.getTeam2().getPlayers().stream().map(Player::getId)
        ).toList();

        matchResultDTO.statistics().forEach(statistic -> {
            if (!validPlayerIds.contains(statistic.playerId())) {
                throw new IllegalArgumentException("Statistic contains an invalid player ID: " + statistic.playerId());
            }
        });

        Set<ObjectId> playerIds = new HashSet<>();
        for (StatisticDTO stat : matchResultDTO.statistics()) {
            if (!playerIds.add(stat.playerId())) {
                throw new IllegalArgumentException("Duplicate statistics found for player ID: " + stat.playerId());
            }
        }

        match.setResult(new MatchResult(
                matchResultDTO.team1Goals(),
                matchResultDTO.team2Goals(),
                matchResultDTO.statistics().stream()
                        .map(dto -> new Statistic(
                                dto.playerId(),
                                dto.assists(),
                                dto.yellowCards(),
                                dto.redCards(),
                                dto.goals()
                        ))
                        .toList()
        ));

        if (matchResultDTO.team1Goals() > matchResultDTO.team2Goals()) {
            match.setWinner(match.getTeam1());
        } else if (matchResultDTO.team2Goals() > matchResultDTO.team1Goals()) {
            match.setWinner(match.getTeam2());
        } else {
            match.setWinner(null);
        }

        Match savedMatch = repository.save(match);
        return mapper.toDto(savedMatch);
    }

//    @Override
//    public List<MatchResponseDTO> getAllMatchResults() {
//        List<Match> matches = repository.findAll();
//
//        return matches.stream()
//                .filter(match -> match.getResult() != null)
//                .map(mapper::toDto)
//                .toList();
//    }

    @Override
    public List<ResultDTO> getAllMatchResults() {
        List<Match> matches = repository.findAll();

        return matches.stream()
                .filter(match -> match.getResult() != null)
                .map(match -> new ResultDTO(
                        match.getRound(),
                        match.getTeam1().getName(),
                        match.getTeam2().getName(),
                        new MatchResultDTO(
                                match.getResult().getTeam1Goals(),
                                match.getResult().getTeam2Goals(),
                                match.getResult().getStatistics().stream()
                                        .map(stat -> new StatisticDTO(
                                                stat.getPlayerId(),
                                                stat.getGoals(),
                                                stat.getAssists(),
                                                stat.getYellowCards(),
                                                stat.getRedCards()
                                        ))
                                        .toList()
                        ),
                        match.getWinner() != null ? match.getWinner().getName() : "Draw"
                ))
                .toList();
    }


}
