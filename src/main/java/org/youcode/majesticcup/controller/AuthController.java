package org.youcode.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.majesticcup.dto.user.LoginRequestDTO;
import org.youcode.majesticcup.dto.user.RegisterRequestDTO;
import org.youcode.majesticcup.dto.user.UserResponseDTO;
import org.youcode.majesticcup.model.collections.Role;
import org.youcode.majesticcup.service.RoleService;
import org.youcode.majesticcup.service.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterRequestDTO dto) {
        UserResponseDTO createdDTO = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
    }
    @PostMapping("/add_role")
    public ResponseEntity<Role> addRole(@RequestBody @Valid Role role) {
        Role addedRole = roleService.addRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRole);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO user) {
        return userService.verify(user);
    }
}
