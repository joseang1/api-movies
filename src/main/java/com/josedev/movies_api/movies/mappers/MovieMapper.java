package com.josedev.movies_api.movies.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import com.josedev.movies_api.actor.ActorEntity;
import com.josedev.movies_api.genre.GenreEntity;
import com.josedev.movies_api.movies.MovieEntity;
import com.josedev.movies_api.movies.dtos.MovieDTORequest;
import com.josedev.movies_api.movies.dtos.MovieDTOResponse;
import com.josedev.movies_api.year.YearEntity;

public class MovieMapper {

    public static MovieEntity toEntity(MovieDTORequest dto, GenreEntity genre, YearEntity year, Set<ActorEntity> actors) {
        MovieEntity movie = new MovieEntity();
        movie.setTitle(dto.title());
        movie.setSynopsis(dto.synopsis());
        movie.setGenre(genre);
        movie.setYear(year);
        movie.setActors(actors);
        return movie;
    }

    public static MovieDTOResponse toDTO(MovieEntity entity) {
        Set<String> actorNames = entity.getActors().stream()
                .map(ActorEntity::getName)
                .collect(Collectors.toSet());
 
        return new MovieDTOResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getSynopsis(),
                entity.getGenre() != null ? entity.getGenre().getName() : null,
                entity.getYear() != null ? entity.getYear().getValue() : null,
                actorNames);
    }

}
