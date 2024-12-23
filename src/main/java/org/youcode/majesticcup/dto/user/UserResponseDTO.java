package org.youcode.majesticcup.dto.user;

import org.bson.types.ObjectId;
import org.youcode.majesticcup.model.collections.Role;

public record UserResponseDTO(
        ObjectId id,
        String username,
        Role role
) {}


