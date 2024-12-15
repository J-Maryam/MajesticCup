package org.youcode.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.majesticcup.dto.result.MatchResultDTO;
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

    @PutMapping("/{id}/result")
    public ResponseEntity<MatchResponseDTO> updateMatchResult(
            @PathVariable("id") ObjectId matchId,
            @RequestBody @Valid MatchResultDTO matchResultDTO
    ) {
        MatchResponseDTO updatedMatch = service.registerMatchResult(matchId, matchResultDTO);
        return ResponseEntity.ok(updatedMatch);
    }
}
