package com.jonathas.challenge.outsera.controller;

import com.jonathas.challenge.outsera.model.dto.AwardIntervalResponseDTO;
import com.jonathas.challenge.outsera.model.dto.MovieDTO;
import com.jonathas.challenge.outsera.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getMovies(
            @RequestParam(required = false) Boolean winner) {
        List<MovieDTO> movies = movieService.getMovies(winner);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/awards/intervals")
    public ResponseEntity<AwardIntervalResponseDTO> getIntervals() {
        AwardIntervalResponseDTO response = movieService.getProducerIntervals();
        return ResponseEntity.ok(response);
    }

}
