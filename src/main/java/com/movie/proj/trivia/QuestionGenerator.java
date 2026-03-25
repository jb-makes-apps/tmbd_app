package com.movie.proj.trivia;

import com.movie.proj.domain.MovieService;
import com.movie.proj.tmdb.TmdbMovieResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class QuestionGenerator {

    private final MovieService movieService;
    private static final Logger logger = LoggerFactory.getLogger(QuestionGenerator.class);


    public QuestionGenerator(MovieService movieService) {
        this.movieService = movieService;
    }

    public GeneratedQuestion generate() {
        long startTime = System.currentTimeMillis();


        try {
            List<TmdbMovieResult> movies = movieService.getRandomMoviePage();
            logger.info("Retrieved {} movies from TMDB in {}ms",
                    movies.size(), System.currentTimeMillis() - startTime);

            if (movies.size() < 4) {
                logger.warn("Expected at least 4 movies but got {}", movies.size());
                throw new IllegalStateException("Not enough movies: " + movies.size());
            }

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
        } catch (Exception e) {
            logger.error("Failed to generate question", e);
            throw e;
        }
    }
}
