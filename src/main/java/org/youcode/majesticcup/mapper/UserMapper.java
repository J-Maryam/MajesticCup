package org.youcode.majesticcup.mapper;

import org.mapstruct.Mapper;
import org.youcode.majesticcup.dto.user.RegisterRequestDTO;
import org.youcode.majesticcup.dto.user.UserResponseDTO;
import org.youcode.majesticcup.model.collections.AppUser;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser toEntity(RegisterRequestDTO dto);
    UserResponseDTO toDto(AppUser appUser);
}
