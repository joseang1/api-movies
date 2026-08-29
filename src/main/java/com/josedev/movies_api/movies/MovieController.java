package com.josedev.movies_api.movies;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josedev.movies_api.movies.dtos.MovieDTORequest;
import com.josedev.movies_api.movies.dtos.MovieDTOResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "${api-endpoint}/movies")
public class MovieController {

    private final InterfaceMovieService service;

    public MovieController(InterfaceMovieService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<MovieDTOResponse> index() {
        return service.getMovies();
    }

    @GetMapping("{id}")
    public MovieDTOResponse getById(@PathVariable Long id) {
        return service.getMovieById(id);
    }

    @PostMapping("")
    public ResponseEntity<MovieDTOResponse> store(@Valid @RequestBody MovieDTORequest dto) {
        MovieDTOResponse movieSaved = service.storeMovie(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    @PutMapping("{id}")
    public ResponseEntity<MovieDTOResponse> update(@PathVariable Long id, @Valid @RequestBody MovieDTORequest dto) {
        MovieDTOResponse movieUpdated = service.updateMovie(id, dto);
        return ResponseEntity.ok(movieUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search")
    public List<MovieDTOResponse> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {
 
        if (title != null)
            return service.getByTitle(title);
 
        if (genre != null)
            return service.getByGenre(genre);
 
        return service.getMovies();
    }

}
 