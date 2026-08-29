package com.josedev.movies_api.movies;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.josedev.movies_api.actor.ActorEntity;
import com.josedev.movies_api.actor.ActorRepository;
import com.josedev.movies_api.genre.GenreEntity;
import com.josedev.movies_api.genre.GenreRepository;
import com.josedev.movies_api.movies.dtos.MovieDTORequest;
import com.josedev.movies_api.movies.dtos.MovieDTOResponse;
import com.josedev.movies_api.movies.exceptions.MovieException;
import com.josedev.movies_api.movies.exceptions.MovieExceptionNotFound;
import com.josedev.movies_api.movies.mappers.MovieMapper;
import com.josedev.movies_api.year.YearEntity;
import com.josedev.movies_api.year.YearRepository;

@Service
public class MovieServiceImpl implements InterfaceMovieService {
 
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final YearRepository yearRepository;
    private final ActorRepository actorRepository;

    public MovieServiceImpl(
            MovieRepository movieRepository,
            GenreRepository genreRepository,
            YearRepository yearRepository,
            ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.yearRepository = yearRepository;
        this.actorRepository = actorRepository;
    }

    @Override
    public List<MovieDTOResponse> getMovies() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    @Override
    public MovieDTOResponse getMovieById(Long id) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Id " + id + " does not exist."));
        return MovieMapper.toDTO(movie);
    }

    @Override
    public MovieDTOResponse storeMovie(MovieDTORequest dto) {
        GenreEntity genre = resolveGenre(dto.genreId());
        YearEntity year = resolveYear(dto.yearId());
        Set<ActorEntity> actors = resolveActors(dto.actorIds());
 
        MovieEntity movieToSave = MovieMapper.toEntity(dto, genre, year, actors);
        MovieEntity movieSaved = movieRepository.save(movieToSave);
 
        return MovieMapper.toDTO(movieSaved);
    }

    @Override
    public MovieDTOResponse updateMovie(Long id, MovieDTORequest dto) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Id " + id + " does not exist."));
 
        GenreEntity genre = resolveGenre(dto.genreId());
        YearEntity year = resolveYear(dto.yearId());
        Set<ActorEntity> actors = resolveActors(dto.actorIds());
 
        movie.setTitle(dto.title());
        movie.setSynopsis(dto.synopsis());
        movie.setGenre(genre);
        movie.setYear(year);
        movie.setActors(actors);
 
        MovieEntity movieUpdated = movieRepository.save(movie);
 
        return MovieMapper.toDTO(movieUpdated);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id))
            throw new MovieExceptionNotFound("Movie not found. Id " + id + " does not exist.");
 
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieDTOResponse> getByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    @Override
    public List<MovieDTOResponse> getByGenre(String genreName) {
        return movieRepository.findByGenreNameIgnoreCase(genreName).stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    private GenreEntity resolveGenre(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new MovieException("Genre not found. Id " + genreId + " does not exist."));
    }

    private YearEntity resolveYear(Long yearId) {
        return yearRepository.findById(yearId)
                .orElseThrow(() -> new MovieException("Year not found. Id " + yearId + " does not exist."));
    }

    private Set<ActorEntity> resolveActors(Set<Long> actorIds) {
        if (actorIds == null || actorIds.isEmpty())
            return new HashSet<>();
 
        return new HashSet<>(actorRepository.findAllById(actorIds));
    }

}
