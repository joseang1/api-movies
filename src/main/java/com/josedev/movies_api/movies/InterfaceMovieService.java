package com.josedev.movies_api.movies;

import java.util.List;

import com.josedev.movies_api.movies.dtos.MovieDTORequest;
import com.josedev.movies_api.movies.dtos.MovieDTOResponse;

public interface InterfaceMovieService {

    List<MovieDTOResponse> getMovies();

    MovieDTOResponse getMovieById(Long id);

    MovieDTOResponse storeMovie(MovieDTORequest dto);

    MovieDTOResponse updateMovie(Long id, MovieDTORequest dto);

    void deleteMovie(Long id);

    List<MovieDTOResponse> getByTitle(String title);

    List<MovieDTOResponse> getByGenre(String genreName);

}
