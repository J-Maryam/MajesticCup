package org.youcode.majesticcup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.majesticcup.dto.result.ResultDTO;
import org.youcode.majesticcup.dto.result.StatisticResponseDTO;
import org.youcode.majesticcup.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class PublicController {
    private final MatchService matchService;

    @GetMapping("/results")
    public ResponseEntity<List<ResultDTO>> getAllMatchResults() {
        List<ResultDTO> matchResults = matchService.getAllMatchResults();
        return ResponseEntity.ok(matchResults);
    }

    @GetMapping("/statistics/top-scorers")
    public ResponseEntity<List<StatisticResponseDTO>> getTopScorers() {
        List<StatisticResponseDTO> topScorers = matchService.getTopScorers();
        return ResponseEntity.ok(topScorers);
    }

    @GetMapping("/statistics/top-assists")
    public ResponseEntity<List<StatisticResponseDTO>> getTopAssists() {
        List<StatisticResponseDTO> topAssists = matchService.getTopAssists();
        return ResponseEntity.ok(topAssists);
    }

    @GetMapping("/statistics/cards")
    public ResponseEntity<List<StatisticResponseDTO>> getTopCards() {
        List<StatisticResponseDTO> topCards = matchService.getTopCards();
        return ResponseEntity.ok(topCards);
    }
}
