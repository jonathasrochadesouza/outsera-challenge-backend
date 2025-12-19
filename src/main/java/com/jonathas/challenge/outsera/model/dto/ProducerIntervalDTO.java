package com.jonathas.challenge.outsera.model.dto;

public record ProducerIntervalDTO(String producer,
                                  Integer interval,
                                  Integer previousWin,
                                  Integer followingWin)
{}
