package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.common.exceptions.EntityNotFoundException;
import org.youcode.majesticcup.dto.match.MatchRequestDTO;
import org.youcode.majesticcup.dto.match.MatchResponseDTO;
import org.youcode.majesticcup.mapper.MatchMapper;
import org.youcode.majesticcup.model.collections.Match;
import org.youcode.majesticcup.repository.MatchRepository;
import org.youcode.majesticcup.repository.TeamRepository;
import org.youcode.majesticcup.service.MatchService;

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
}
