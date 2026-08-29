package com.josedev.movies_api.movies.dtos;

import java.util.Set;

public record MovieDTOResponse(
    Long id,
    String title,
    String synopsis,
    String genreName,
    Integer year,
    Set<String> actorNames) {
}