package org.youcode.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.youcode.majesticcup.dto.team.TeamRequestDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;
import org.youcode.majesticcup.model.collections.Team;

@Mapper(
        componentModel = "spring",
        uses = {PlayerMapper.class}
)
public interface TeamMapper {
    Team toEntity(TeamRequestDTO dto);

    TeamResponseDTO toDto(Team team);
}
