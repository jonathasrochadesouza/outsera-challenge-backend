package com.jonathas.challenge.outsera.model.dto;

import com.jonathas.challenge.outsera.model.entity.MovieEntity;

import java.util.List;

public record AwardIntervalResponseDTO(List<ProducerIntervalDTO> min,
                                       List<ProducerIntervalDTO> max)
{}
