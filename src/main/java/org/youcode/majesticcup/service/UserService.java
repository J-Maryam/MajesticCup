package org.youcode.majesticcup.service;

import org.youcode.majesticcup.dto.user.LoginRequestDTO;
import org.youcode.majesticcup.dto.user.RegisterRequestDTO;
import org.youcode.majesticcup.dto.user.UserResponseDTO;
import org.youcode.majesticcup.model.collections.AppUser;

public interface UserService {
    UserResponseDTO register(RegisterRequestDTO dto);

//        AppUser authenticate(LoginRequestDTO dto);
    String verify(LoginRequestDTO user);
}
