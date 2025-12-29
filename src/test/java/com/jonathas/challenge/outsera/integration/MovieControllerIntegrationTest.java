package com.jonathas.challenge.outsera.integration;

import com.jonathas.challenge.outsera.model.dto.IntervalsResponseDTO;
import com.jonathas.challenge.outsera.model.dto.ProducerIntervalDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void winIntervalMax_forProducer_test() {
        ResponseEntity<IntervalsResponseDTO> response = restTemplate
                .getForEntity("/api/intervals", IntervalsResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        IntervalsResponseDTO intervals = response.getBody();

        assertThat(intervals.max()).isNotNull();
        assertThat(intervals.max()).isNotEmpty();

        assertThat(intervals.max())
                .anyMatch(p ->
                        p.producer().equals("Matthew Vaughn") &&
                                p.interval() == 13 &&
                                p.previousWin() == 2002 &&
                                p.followingWin() == 2015
                );
    }

    @Test
    void winIntervalMin_forProducer_test() {
        ResponseEntity<IntervalsResponseDTO> response = restTemplate
                .getForEntity("/api/intervals", IntervalsResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        IntervalsResponseDTO intervals = response.getBody();

        assertThat(intervals.min()).isNotNull();
        assertThat(intervals.min()).isNotEmpty();

        assertThat(intervals.min())
                .anyMatch(p ->
                        p.producer().equals("Joel Silver") &&
                                p.interval() == 1 &&
                                p.previousWin() == 1990 &&
                                p.followingWin() == 1991
                );
    }

    @Test
    void minimumIntervalTest() {
        ResponseEntity<IntervalsResponseDTO> response = restTemplate
                .getForEntity("/api/intervals", IntervalsResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        List<ProducerIntervalDTO> minList = response.getBody().min();
        assertThat(minList).isNotEmpty();

        int minInterval = minList.get(0).interval();
        assertThat(minInterval).isEqualTo(1);

        assertThat(minList).allMatch(p -> p.interval() == minInterval);
    }

    @Test
    void maximumIntervalTest() {
        ResponseEntity<IntervalsResponseDTO> response = restTemplate
                .getForEntity("/api/intervals", IntervalsResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        List<ProducerIntervalDTO> maxList = response.getBody().max();
        assertThat(maxList).isNotEmpty();

        int maxInterval = maxList.get(0).interval();
        assertThat(maxInterval).isEqualTo(13);

        assertThat(maxList).allMatch(p -> p.interval() == maxInterval);
    }

    @Test
    void intervalMinStructureTest() {
        ResponseEntity<IntervalsResponseDTO> response = restTemplate
                .getForEntity("/api/intervals", IntervalsResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        response.getBody().min().forEach(p -> {
            assertThat(p.producer()).isNotBlank();
            assertThat(p.interval()).isGreaterThanOrEqualTo(1);
            assertThat(p.followingWin()).isGreaterThan(p.previousWin());
            assertThat(p.followingWin() - p.previousWin()).isEqualTo(p.interval());
        });
    }

    @Test
    void intervalMaxStructureTest() {
        ResponseEntity<IntervalsResponseDTO> response = restTemplate
                .getForEntity("/api/intervals", IntervalsResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        response.getBody().max().forEach(p -> {
            assertThat(p.producer()).isNotBlank();
            assertThat(p.interval()).isGreaterThanOrEqualTo(1);
            assertThat(p.followingWin()).isGreaterThan(p.previousWin());
            assertThat(p.followingWin() - p.previousWin()).isEqualTo(p.interval());
        });
    }

    @Test
    void listAllMoviesTest() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/movies", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void moviesFilterWinnersTest() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/movies?winner=true", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("\"winner\":true");
    }
}