package org.youcode.majesticcup.dto.user;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public record RegisterRequestDTO(
        @NotBlank(message = "Username cannot be blank.")
        String username,

        @NotBlank(message = "Password cannot be blank.")
        String password,

//        @NotBlank(message = "Role cannot be blank.")
        ObjectId role
) {}

