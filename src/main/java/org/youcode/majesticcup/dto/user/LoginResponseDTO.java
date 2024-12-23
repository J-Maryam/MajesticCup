package org.youcode.majesticcup.dto.user;

public record LoginResponseDTO(
        String token,
        long expiresIn
) {
}
