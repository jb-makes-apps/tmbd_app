package com.movie.proj.trivia;

import com.movie.proj.domain.MovieService;
import com.movie.proj.tmdb.TmdbMovieResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;


@Component
public class QuestionGenerator {

    private final MovieService movieService;

    public QuestionGenerator(MovieService movieService) {
        this.movieService = movieService;
    }

    public TriviaQuestion generate(){
        ArrayList<String> titles= new ArrayList<String>();

        TmdbMovieResult tmdbMovieResult =  movieService.getRandomMovie();
        for(int i = 0; i < 3; i++){
            titles.add(movieService.getRandomMovie().getTitle());
        }
        titles.add(tmdbMovieResult.getTitle());
        Collections.shuffle(titles);
        String redacted = tmdbMovieResult.getOverview().replace(tmdbMovieResult.getTitle(), "---------");


        return new TriviaQuestion(UUID.randomUUID().toString(), redacted, titles);
    }
}
