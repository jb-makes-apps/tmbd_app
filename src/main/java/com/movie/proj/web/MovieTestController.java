package com.movie.proj.web;

import com.movie.proj.domain.MovieService;
import com.movie.proj.tmdb.TmdbMovieDetailsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")


public class MovieTestController {

    private final MovieService movieService;

    public MovieTestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{id}")
    public TmdbMovieDetailsResponse testMovie(@PathVariable long id) {
        return movieService.getMovie(id);
    }
}
