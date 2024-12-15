package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.common.exceptions.EntityNotFoundException;
import org.youcode.majesticcup.dto.competition.CompetitionRequestDTO;
import org.youcode.majesticcup.dto.competition.CompetitionResponseDTO;
import org.youcode.majesticcup.mapper.CompetitionMapper;
import org.youcode.majesticcup.model.collections.Competition;
import org.youcode.majesticcup.model.collections.Team;
import org.youcode.majesticcup.repository.CompetitionRepository;
import org.youcode.majesticcup.repository.TeamRepository;
import org.youcode.majesticcup.service.CompetitionService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository repository;
    private final CompetitionMapper mapper;
    private final TeamRepository teamRepository;


    @Override
    public CompetitionResponseDTO createCompetition(CompetitionRequestDTO dto) {
        List<Team> teams = dto.teamIds().stream()
                .map(teamId -> teamRepository.findById(teamId)
                        .orElseThrow(() -> new EntityNotFoundException("Team not found with ID: " + teamId)))
                .toList();

        if (teams.size() != dto.numberOfTeams()) {
            throw new IllegalArgumentException("The number of provided teams does not match the specified number of teams.");
        }

        Competition competition = new Competition();
        competition.setName(dto.name());
        competition.setNumberOfTeams(dto.numberOfTeams());
        competition.setTeams(teams);
        competition.setCurrentRound(0);
        competition.setRounds(List.of());

        Competition savedCompetition = repository.save(competition);

        return mapper.toDto(savedCompetition);
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

        if (teams.size() != dto.numberOfTeams()) {
            throw new IllegalArgumentException("The number of provided teams does not match the specified number of teams.");
        }

        competition.setName(dto.name());
        competition.setNumberOfTeams(dto.numberOfTeams());
        competition.setTeams(teams);

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
