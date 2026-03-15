package com.movie.proj.trivia;

import com.movie.proj.domain.MovieService;
import com.movie.proj.tmdb.TmdbMovieResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionGenerator {
    private List<String> titles;

    private final MovieService movieService;

    public QuestionGenerator(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<String> generate(){

        TmdbMovieResult tmdbMovieResult =  movieService.getRandomMovie();
        for(int i = 0; i < 3; i++){
            titles.add(movieService.getRandomMovie().getTitle());
        }
        return titles;
    }
}
