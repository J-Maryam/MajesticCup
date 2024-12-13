package org.youcode.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.majesticcup.dto.team.TeamRequestDTO;
import org.youcode.majesticcup.dto.team.TeamResponseDTO;
import org.youcode.majesticcup.model.sub_document.Player;
import org.youcode.majesticcup.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamResponseDTO> createTeam(@RequestBody @Valid TeamRequestDTO request) {
        TeamResponseDTO createdTeam = teamService.createTeam(request);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> updateTeam(
            @PathVariable ObjectId id,
            @RequestBody @Valid TeamRequestDTO request) {
        TeamResponseDTO updatedTeam = teamService.updateTeam(id, request);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable ObjectId id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<TeamResponseDTO> teams = teamService.getTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable ObjectId id) {
        TeamResponseDTO team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @PatchMapping("/{teamId}/players")
    public ResponseEntity<Void> addPlayerToTeam(
            @PathVariable ObjectId teamId,
            @RequestBody @Valid Player player) {
        teamService.addPlayerToTeam(teamId, player);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{teamId}/players/{playerId}")
    public ResponseEntity<Void> removePlayerFromTeam(
            @PathVariable ObjectId teamId,
            @PathVariable ObjectId playerId) {
        teamService.removePlayerFromTeam(teamId, playerId);
        return ResponseEntity.noContent().build();
    }


}