package com.movie.proj.trivia;

import com.movie.proj.domain.MovieService;
import com.movie.proj.tmdb.TmdbMovieResult;
import org.springframework.stereotype.Component;

@Component
public class QuestionGenerator {

    private final MovieService movieService;

    public QuestionGenerator(MovieService movieService) {
        this.movieService = movieService;
    }


    public TriviaQuestion generate(){
        TmdbMovieResult tmdbMovieResult =  movieService.getRandomMovie();
    }
}
