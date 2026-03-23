package com.movie.proj.trivia;

import com.movie.proj.domain.MovieService;
import com.movie.proj.tmdb.TmdbMovieResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class QuestionGenerator {

    private final MovieService movieService;

    public QuestionGenerator(MovieService movieService) {
        this.movieService = movieService;
    }

    public GeneratedQuestion generate() {
        List<TmdbMovieResult> movies = movieService.getRandomMoviePage();

        Collections.shuffle(movies);

        List<TmdbMovieResult> selected = movies.subList(0, 4);

        TmdbMovieResult correct = selected.get(0);
        List<String> titles = selected.stream()
                .map(TmdbMovieResult::getTitle)
                .collect(Collectors.toCollection(ArrayList::new));


        String redacted = correct.getOverview()
                .replace(correct.getTitle(), "---------");

        return new GeneratedQuestion(
                new TriviaQuestion(UUID.randomUUID().toString(), redacted, titles),
                correct.getTitle()
        );
    }
}
