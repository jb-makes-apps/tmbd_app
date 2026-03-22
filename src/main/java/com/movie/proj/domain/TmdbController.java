package com.movie.proj.domain;

import com.movie.proj.tmdb.TmdbMovieResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tmdb")
public class TmdbController {

    private final MovieService movieService;

    public TmdbController(MovieService movieService) {
        this.movieService = movieService;
    }


}
