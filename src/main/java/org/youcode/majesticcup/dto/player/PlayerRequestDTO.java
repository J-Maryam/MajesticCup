package org.youcode.majesticcup.dto.player;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public record PlayerRequestDTO(
        ObjectId id,

        @NotBlank(message = "The player's name cannot be blank.")
        @Size(min = 2, max = 50, message = "The player's name must be between 2 and 50 characters.")
        String name,

        @NotBlank(message = "The player's surname cannot be blank.")
        @Size(min = 2, max = 50, message = "The player's surname must be between 2 and 50 characters.")
        String surname,

        @NotBlank(message = "The player's position cannot be blank.")
        @Size(max = 20, message = "The player's position must not exceed 20 characters.")
        String position,

        @Positive(message = "The player's number must be a positive integer.")
        int number
) {}
