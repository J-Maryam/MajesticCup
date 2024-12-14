package org.youcode.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.majesticcup.dto.match.MatchRequestDTO;
import org.youcode.majesticcup.dto.match.MatchResponseDTO;
import org.youcode.majesticcup.service.MatchService;

@RestController
@RequestMapping("/api/admin/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService service;

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody @Valid MatchRequestDTO dto) {
        MatchResponseDTO match = service.createMatch(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }
}
