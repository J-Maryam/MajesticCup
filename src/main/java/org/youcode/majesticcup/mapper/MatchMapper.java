package org.youcode.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.youcode.majesticcup.dto.match.MatchRequestDTO;
import org.youcode.majesticcup.dto.match.MatchResponseDTO;
import org.youcode.majesticcup.model.collections.Match;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    Match toEntity(MatchRequestDTO dto);
    MatchResponseDTO toDto(Match match);
}
