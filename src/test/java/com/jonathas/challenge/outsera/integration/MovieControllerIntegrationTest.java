package com.jonathas.challenge.outsera.integration;

import com.jonathas.challenge.outsera.model.dto.IntervalsResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void intervalsEndPoint_Min_Test() {
        ResponseEntity<IntervalsResponseDTO> response = restTemplate
                .getForEntity("/api/intervals", IntervalsResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().min()).isNotNull();

        response.getBody().min().forEach(p -> {
            assertThat(p.producer()).isNotBlank();
            assertThat(p.interval()).isGreaterThanOrEqualTo(1);
            assertThat(p.followingWin()).isGreaterThan(p.previousWin());
        });
    }

    @Test
    void intervalsEndPoint_Max_Test() {
        ResponseEntity<IntervalsResponseDTO> response = restTemplate
                .getForEntity("/api/intervals", IntervalsResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().max()).isNotNull();

        response.getBody().max().forEach(p -> {
            assertThat(p.producer()).isNotBlank();
            assertThat(p.interval()).isGreaterThanOrEqualTo(1);
            assertThat(p.followingWin()).isGreaterThan(p.previousWin());
        });
    }

    @Test
    void moviesEndpointTest() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/movies", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void moviesWinnersParamsTest() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/movies?winner=true", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("\"winner\":true");
    }
}