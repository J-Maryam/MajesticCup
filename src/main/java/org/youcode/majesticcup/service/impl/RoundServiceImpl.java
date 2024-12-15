    package org.youcode.majesticcup.service.impl;

    import lombok.RequiredArgsConstructor;
    import org.bson.types.ObjectId;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import org.youcode.majesticcup.common.exceptions.EntityNotFoundException;
    import org.youcode.majesticcup.dto.round.RoundRequestDTO;
    import org.youcode.majesticcup.dto.round.RoundResponseDTO;
    import org.youcode.majesticcup.mapper.RoundMapper;
    import org.youcode.majesticcup.model.collections.Competition;
    import org.youcode.majesticcup.model.collections.Match;
    import org.youcode.majesticcup.model.collections.Round;
    import org.youcode.majesticcup.repository.CompetitionRepository;
    import org.youcode.majesticcup.repository.MatchRepository;
    import org.youcode.majesticcup.repository.RoundRepository;
    import org.youcode.majesticcup.repository.TeamRepository;
    import org.youcode.majesticcup.service.RoundService;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @Transactional
    @RequiredArgsConstructor
    public class RoundServiceImpl implements RoundService {

        private final RoundRepository repository;
        private final RoundMapper mapper;
        private final MatchRepository matchRepository;
        private final TeamRepository teamRepository;
        private final CompetitionRepository competitionRepository;

        @Override
        public RoundResponseDTO createRound(RoundRequestDTO dto) {
            Competition competition = competitionRepository.findById(dto.competitionId())
                    .orElseThrow(() -> new EntityNotFoundException("Competition not found with ID: " + dto.competitionId()));

            List<Match> matches = dto.matches().stream()
                    .map(matchId -> matchRepository.findById((matchId))
                            .orElseThrow(() -> new EntityNotFoundException("Match not found with ID: " + matchId)))
                    .collect(Collectors.toList());

            Round round = new Round();
            round.setRoundNumber(dto.roundNumber());
            round.setCompetitionId(competition);
            round.setMatches(matches);

            Round savedRound = repository.save(round);
            return mapper.toDto(savedRound);
        }

        @Override
        public List<RoundResponseDTO> getRoundsByCompetition(ObjectId competitionId) {
            Competition competition = competitionRepository.findById(competitionId)
                    .orElseThrow(() -> new EntityNotFoundException("Competition not found with ID: " + competitionId));

            List<Round> rounds = repository.findByCompetitionId(competition);
            return rounds.stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
        }

        @Override
        public RoundResponseDTO getRoundById(ObjectId roundId) {
            Round round = repository.findById(roundId)
                    .orElseThrow(() -> new EntityNotFoundException("Round not found with ID: " + roundId));
            return mapper.toDto(round);
        }

        @Override
        public void deleteRound(ObjectId roundId) {
            Round round = repository.findById(roundId)
                    .orElseThrow(() -> new EntityNotFoundException("Round not found with ID: " + roundId));
            repository.delete(round);
        }

    }
