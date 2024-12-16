package org.youcode.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.youcode.majesticcup.dto.result.MatchResultDTO;
import org.youcode.majesticcup.model.sub_document.MatchResult;

@Mapper(componentModel = "spring", uses = {StatisticMapper.class})
public interface MatchResultMapper {
    MatchResult toEntity(MatchResultDTO dto);
    MatchResultDTO toDto(MatchResult entity);
}
