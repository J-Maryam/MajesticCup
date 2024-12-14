package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.dto.competition.CompetitionRequestDTO;
import org.youcode.majesticcup.dto.competition.CompetitionResponseDTO;
import org.youcode.majesticcup.mapper.CompetitionMapper;
import org.youcode.majesticcup.repository.CompetitionRepository;
import org.youcode.majesticcup.service.CompetitionService;

@Service
@Transactional
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository repository;
    private final CompetitionMapper mapper;


    @Override
    public CompetitionResponseDTO createCompetition(CompetitionRequestDTO dto) {
        return null;
    }

}
