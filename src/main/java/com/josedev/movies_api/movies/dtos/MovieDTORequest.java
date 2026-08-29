package com.josedev.movies_api.movies.dtos;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieDTORequest(
    @NotBlank(message = "Title cannot be empty")
    @NotNull(message = "Title cannot be null")
    String title,

    String synopsis,

    @NotNull(message = "Genre id cannot be null")
    Long genreId,

    @NotNull(message = "Year id cannot be null")
    Long yearId,

    Set<Long> actorIds) {
}
