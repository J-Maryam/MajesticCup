package org.youcode.majesticcup.dto;

public record StatisticDTO(
        String playerId,
        int goals,
        int assists,
        int yellowCards,
        int redCards
) {}

