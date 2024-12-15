package org.youcode.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.youcode.majesticcup.dto.competition.CompetitionRequestDTO;
import org.youcode.majesticcup.dto.competition.CompetitionResponseDTO;
import org.youcode.majesticcup.model.collections.Competition;

@Mapper(componentModel = "spring", uses = {TeamMapper.class, RoundMapper.class})
public interface CompetitionMapper {
    Competition toEntity(CompetitionRequestDTO dto);
    CompetitionResponseDTO toDto(Competition team);
}
