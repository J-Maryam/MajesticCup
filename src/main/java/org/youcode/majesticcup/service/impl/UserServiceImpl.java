package org.youcode.majesticcup.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.youcode.majesticcup.common.exceptions.business.EntityNotFoundException;
import org.youcode.majesticcup.dto.user.LoginRequestDTO;
import org.youcode.majesticcup.dto.user.RegisterRequestDTO;
import org.youcode.majesticcup.dto.user.UserResponseDTO;
import org.youcode.majesticcup.common.exceptions.business.UsernameAlreadyExistsException;
import org.youcode.majesticcup.mapper.UserMapper;
import org.youcode.majesticcup.model.collections.AppUser;
import org.youcode.majesticcup.model.collections.Role;
import org.youcode.majesticcup.repository.RoleRepository;
import org.youcode.majesticcup.repository.UserRepository;
import org.youcode.majesticcup.security.JWTService;
import org.youcode.majesticcup.service.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public UserResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username '" + dto.username() + "' already exists");
        }

        Role role = roleRepository.findByName("ROLE_OPERATOR").orElseThrow(() -> new EntityNotFoundException("Role '" + dto.username() + "' not found"));

        AppUser user = userMapper.toEntity(dto);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(dto.password()));

        AppUser savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public String verify(LoginRequestDTO user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.username(), user.password()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.username(), user.password());
            }
        } catch (Exception e) {
            return "Authentication failed: " + e.getMessage();
        }
        return "Fails";
    }

}
