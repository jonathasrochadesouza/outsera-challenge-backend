package com.jonathas.challenge.outsera.model.dto;

import java.util.List;

public record IntervalsResponseDTO(List<ProducerIntervalDTO> min,
                                   List<ProducerIntervalDTO> max)
{}
