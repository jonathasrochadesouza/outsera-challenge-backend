package com.jonathas.challenge.outsera.model.dto;

import com.jonathas.challenge.outsera.model.entity.MovieEntity;

import java.util.UUID;

public record MovieDTO(UUID id,
                       Integer yearDate,
                       String title,
                       String studios,
                       String producers,
                       boolean winner)
{

    public static MovieDTO fromEntity(MovieEntity movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getYearDate(),
                movie.getTitle(),
                movie.getStudio(),
                movie.getProducer(),
                movie.isWinner()
        );
    }

}
