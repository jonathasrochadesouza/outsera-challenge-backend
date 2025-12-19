package com.jonathas.challenge.outsera.config;

import com.jonathas.challenge.outsera.model.entity.MovieEntity;
import com.jonathas.challenge.outsera.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class LoaderCSV {

    private final MovieRepository movieRepository;

    public LoaderCSV(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() {
        try {
            InputStream inputStream = getClass()
                    .getResourceAsStream("/movielist.csv");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );

            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                MovieEntity movie = MovieEntity.builder()
                    .yearDate(Integer.parseInt(fields[0]))
                    .title(fields[1])
                    .studios(fields[2])
                    .producers(fields[3])
                    .winner(fields.length > 4 && "yes".equalsIgnoreCase(fields[4]))
                    .build();

                movieRepository.save(movie);
            }

            reader.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar CSV", e);
        }
    }

}
