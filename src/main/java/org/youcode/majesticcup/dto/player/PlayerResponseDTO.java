package org.youcode.majesticcup.dto.player;

import org.bson.types.ObjectId;

public record PlayerResponseDTO(
        ObjectId id,
        String name,
        String surname,
        String position,
        int number
) {
}
