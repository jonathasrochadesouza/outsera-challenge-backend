package com.jonathas.challenge.outsera.service;

import com.jonathas.challenge.outsera.model.dto.AwardIntervalResponseDTO;
import com.jonathas.challenge.outsera.model.dto.MovieDTO;
import com.jonathas.challenge.outsera.model.dto.ProducerIntervalDTO;
import com.jonathas.challenge.outsera.repository.MovieRepository;
import com.jonathas.challenge.outsera.util.ProducerParser;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MovieDTO> getMovies(Boolean winner) {
        var movies = repository.findAll();

        if (Boolean.TRUE.equals(winner)) {
            movies = repository.findByWinnerTrue();
        }

        return movies.stream()
            .map(MovieDTO::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public AwardIntervalResponseDTO getProducerIntervals() {
        var winners = repository.findByWinnerTrue();

        Map<String, List<Integer>> winnersByGroupProducerAndYear = winners.stream()
                .flatMap(movie -> ProducerParser.parse(movie.getProducers())
                        .stream()
                        .map(producer -> new AbstractMap.SimpleEntry<>(producer, movie.getYearDate()))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));

        List<ProducerIntervalDTO> intervals = winnersByGroupProducerAndYear.entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .flatMap(entry -> {
                    String producer = entry.getKey();
                    List<Integer> years = entry.getValue().stream().sorted().toList();

                    return IntStream.range(0, years.size() - 1)
                            .mapToObj(i -> new ProducerIntervalDTO(
                                    producer,
                                    years.get(i + 1) - years.get(i),
                                    years.get(i),
                                    years.get(i + 1)
                            ));
                })
                .toList();

        if (intervals.isEmpty()) {
            return new AwardIntervalResponseDTO(List.of(), List.of());
        }

        int minInterval = intervals.stream()
                .mapToInt(ProducerIntervalDTO::interval)
                .min()
                .orElse(0);

        int maxInterval = intervals.stream()
                .mapToInt(ProducerIntervalDTO::interval)
                .max()
                .orElse(0);

        List<ProducerIntervalDTO> minProducerIntervals = intervals.stream()
                .filter(dto -> dto.interval() == minInterval).toList();
        List<ProducerIntervalDTO> maxProducerIntervals = intervals.stream()
                .filter(dto -> dto.interval() == maxInterval).toList();

        return new AwardIntervalResponseDTO(minProducerIntervals, maxProducerIntervals);
    }

}
