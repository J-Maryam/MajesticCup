package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.common.exceptions.business.EntityNotFoundException;
import org.youcode.majesticcup.dto.competition.CompetitionRequestDTO;
import org.youcode.majesticcup.dto.competition.CompetitionResponseDTO;
import org.youcode.majesticcup.mapper.CompetitionMapper;
import org.youcode.majesticcup.model.collections.Competition;
import org.youcode.majesticcup.model.collections.Round;
import org.youcode.majesticcup.model.collections.Team;
import org.youcode.majesticcup.repository.CompetitionRepository;
import org.youcode.majesticcup.repository.RoundRepository;
import org.youcode.majesticcup.repository.TeamRepository;
import org.youcode.majesticcup.service.CompetitionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository repository;
    private final CompetitionMapper mapper;
    private final TeamRepository teamRepository;
    private final RoundRepository roundRepository;

    @Override
    public CompetitionResponseDTO createCompetition(CompetitionRequestDTO dto) {
        validateUniqueTeams(dto.teamIds());
        validateUniqueRounds(dto.roundIds());

        List<Team> teams = dto.teamIds().stream()
                .map(teamId -> teamRepository.findById(teamId)
                        .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId)))
                .toList();

        List<Round> rounds = dto.roundIds().stream()
                .map(roundId -> roundRepository.findById(roundId)
                        .orElseThrow(() -> new EntityNotFoundException("Round not found with Id " + roundId)))
                .toList();

        if (teams.size() != dto.numberOfTeams()) {
            throw new IllegalArgumentException("The number of provided teams does not match the specified number of teams.");
        }

        Competition competition = new Competition();
        competition.setName(dto.name());
        competition.setNumberOfTeams(dto.numberOfTeams());
        competition.setTeams(teams);
        competition.setCurrentRound(0);
        competition.setRounds(rounds);

        Competition savedCompetition = repository.save(competition);

        return mapper.toDto(savedCompetition);
    }

    private void validateUniqueTeams(List<ObjectId> teamIds) {
        Set<ObjectId> teamSet = new HashSet<>();
        teamIds.stream()
                .filter(teamId -> !teamSet.add(teamId))
                .findFirst()
                .ifPresent(duplicateTeamId -> {
                    throw new IllegalArgumentException("Duplicate team ID detected: " + duplicateTeamId);
                });
    }

    private void validateUniqueRounds(List<ObjectId> roundIds) {
        Set<ObjectId> roundSet = new HashSet<>();
        roundIds.stream()
                .filter(roundId -> !roundSet.add(roundId))
                .findFirst()
                .ifPresent(duplicateRoundId -> {
                    throw new IllegalArgumentException("Duplicate round ID detected: " + duplicateRoundId);
                });
    }

    @Override
    public CompetitionResponseDTO getCompetitionById(ObjectId id) {
        Competition competition = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Competition not found with ID: " + id));
        return mapper.toDto(competition);
    }

    @Override
    public List<CompetitionResponseDTO> getAllCompetitions() {
        List<Competition> competitions = repository.findAll();
        return competitions.stream()
                .map(mapper::toDto)
                .toList();
    }


    @Override
    public CompetitionResponseDTO updateCompetition(ObjectId id, CompetitionRequestDTO dto) {
        Competition competition = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Competition not found with ID: " + id));

        List<Team> teams = dto.teamIds().stream()
                .map(teamId -> teamRepository.findById(teamId)
                        .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId)))
                .toList();

        List<Round> rounds = dto.roundIds().stream()
                .map(roundId -> roundRepository.findById(roundId)
                        .orElseThrow(() -> new EntityNotFoundException("Round not found with Id " + roundId)))
                .toList();

        if (teams.size() != dto.numberOfTeams()) {
            throw new IllegalArgumentException("The number of provided teams does not match the specified number of teams.");
        }

        competition.setName(dto.name());
        competition.setNumberOfTeams(dto.numberOfTeams());
        competition.setTeams(teams);
        competition.setRounds(rounds);

        Competition updatedCompetition = repository.save(competition);

        return mapper.toDto(updatedCompetition);
    }

    @Override
    public void deleteCompetition(ObjectId id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Competition not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
