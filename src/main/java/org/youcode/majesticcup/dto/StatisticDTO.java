package org.youcode.majesticcup.dto;

import org.bson.types.ObjectId;

public record StatisticDTO(
        ObjectId playerId,
        int goals,
        int assists,
        int yellowCards,
        int redCards
) {}
