package com.jonathas.challenge.outsera.service;

import com.jonathas.challenge.outsera.model.dto.AwardIntervalResponseDTO;
import com.jonathas.challenge.outsera.model.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieDTO> getMovies(Boolean winner);

    AwardIntervalResponseDTO getProducerIntervals();

}
