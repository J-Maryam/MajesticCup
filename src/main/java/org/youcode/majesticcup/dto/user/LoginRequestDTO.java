package org.youcode.majesticcup.dto.user;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
