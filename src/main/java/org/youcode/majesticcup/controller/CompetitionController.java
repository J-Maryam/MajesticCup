package org.youcode.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.majesticcup.dto.competition.CompetitionRequestDTO;
import org.youcode.majesticcup.dto.competition.CompetitionResponseDTO;
import org.youcode.majesticcup.service.CompetitionService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/competitions")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService service;

    @PostMapping
    public ResponseEntity<CompetitionResponseDTO> createCompetition(@RequestBody @Valid CompetitionRequestDTO dto) {
        CompetitionResponseDTO response = service.createCompetition(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionResponseDTO> getCompetitionById(@PathVariable ObjectId id) {
        CompetitionResponseDTO response = service.getCompetitionById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDTO>> getAllCompetitions() {
        List<CompetitionResponseDTO> response = service.getAllCompetitions();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionResponseDTO> updateCompetition(
            @PathVariable("id") ObjectId competitionId,
            @RequestBody CompetitionRequestDTO dto) {
        CompetitionResponseDTO updatedCompetition = service.updateCompetition(competitionId, dto);
        return ResponseEntity.ok(updatedCompetition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable ObjectId id) {
        service.deleteCompetition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
