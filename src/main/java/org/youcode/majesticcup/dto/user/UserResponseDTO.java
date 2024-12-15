package org.youcode.majesticcup.dto.user;

import org.bson.types.ObjectId;

public record UserResponseDTO(
        ObjectId id,
        String username,
        ObjectId role
) {}


