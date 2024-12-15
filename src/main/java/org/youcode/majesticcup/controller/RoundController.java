package org.youcode.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.majesticcup.dto.round.RoundRequestDTO;
import org.youcode.majesticcup.dto.round.RoundResponseDTO;
import org.youcode.majesticcup.service.RoundService;

import java.util.List;

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

    @GetMapping("/competition/{competitionId}")
    public ResponseEntity<List<RoundResponseDTO>> getRoundsByCompetition(@PathVariable ObjectId competitionId) {
        return ResponseEntity.ok(service.getRoundsByCompetition(competitionId));
    }

    @GetMapping("/{roundId}")
    public ResponseEntity<RoundResponseDTO> getRoundById(@PathVariable ObjectId roundId) {
        return ResponseEntity.ok(service.getRoundById(roundId));
    }

    @DeleteMapping("/{roundId}")
    public ResponseEntity<Void> deleteRound(@PathVariable ObjectId roundId) {
        service.deleteRound(roundId);
        return ResponseEntity.noContent().build();
    }
}
