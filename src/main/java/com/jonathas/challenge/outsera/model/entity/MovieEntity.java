package com.jonathas.challenge.outsera.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@lombok.Data
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity {

    @Id @GeneratedValue
    private UUID id;
    private Integer yearDate;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;

}
