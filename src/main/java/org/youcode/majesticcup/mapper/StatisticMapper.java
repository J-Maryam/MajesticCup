package org.youcode.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.youcode.majesticcup.dto.StatisticDTO;
import org.youcode.majesticcup.model.sub_document.Statistic;

@Mapper(componentModel = "spring")
public interface StatisticMapper {
    Statistic toEntity(StatisticDTO statisticDTO);
    StatisticDTO toDTO(Statistic statistic);
}
