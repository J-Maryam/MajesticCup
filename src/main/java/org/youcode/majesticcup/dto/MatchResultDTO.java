package org.youcode.majesticcup.dto;

import java.util.List;

public record MatchResultDTO(
        int team1Goals,
        int team2Goals,
        List<StatisticDTO> statistics
) {}

