package org.youcode.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.majesticcup.dto.round.RoundRequestDTO;
import org.youcode.majesticcup.dto.round.RoundResponseDTO;
import org.youcode.majesticcup.service.RoundService;

@RestController
@RequestMapping("/api/admin/rounds")
@RequiredArgsConstructor
public class RoundController {
    private final RoundService service;

    @PostMapping
    public ResponseEntity<RoundResponseDTO> createRound(@RequestBody @Valid RoundRequestDTO requestDTO) {
        RoundResponseDTO createdRound = service.createRound(requestDTO);
        return ResponseEntity.ok(createdRound);
    }

}
