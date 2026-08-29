package com.josedev.movies_api.movies;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findByTitle(String title);

    List<MovieEntity> findByTitleContainingIgnoreCase(String title);

    List<MovieEntity> findByGenreNameIgnoreCase(String genreName);

    @Query("SELECT m FROM MovieEntity m WHERE LOWER(m.genre.name) = LOWER(:genreName)")
    List<MovieEntity> findByGenre(@Param("genreName") String genreName);
}