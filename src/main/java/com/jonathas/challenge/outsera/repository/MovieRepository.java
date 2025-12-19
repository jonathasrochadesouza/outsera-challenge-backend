package com.jonathas.challenge.outsera.repository;

import com.jonathas.challenge.outsera.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {

    List<MovieEntity> findByWinnerTrue();

}
