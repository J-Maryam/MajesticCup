package org.youcode.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.youcode.majesticcup.dto.round.RoundRequestDTO;
import org.youcode.majesticcup.dto.round.RoundResponseDTO;
import org.youcode.majesticcup.model.collections.Round;

@Mapper(componentModel = "spring", uses = {MatchMapper.class})
public interface RoundMapper {
    Round toEntity(RoundRequestDTO dto);
    RoundResponseDTO toDto(Round entity);
}
