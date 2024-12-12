package org.youcode.majesticcup.dto.team;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.dto.player.PlayerResponseDTO;

import java.util.List;

public record TeamResponseDTO(
        ObjectId id,
        String name,
        String city,
        List<PlayerResponseDTO> players
) {
}
