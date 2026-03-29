package com.movie.proj;

import com.movie.proj.domain.MovieService;
import com.movie.proj.tmdb.TmdbMovieResult;
import com.movie.proj.trivia.GeneratedQuestion;
import com.movie.proj.trivia.QuestionGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionGeneratorTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private QuestionGenerator questionGenerator;

    private TmdbMovieResult createFakeMovie(String title, String overview) {
        TmdbMovieResult movie = new TmdbMovieResult();
        movie.setTitle(title);
        movie.setOverview(overview);
        return movie;
    }


    @Test
    void shouldGenerateQuestionWithFourOptions() {

        List<TmdbMovieResult> fakeMovies = Arrays.asList(
                createFakeMovie("Avengers", "Avengers save the world from " + "Avengers" + " villain"),
                createFakeMovie("Hulk", "A Hulk scientist transforms into a monster"),
                createFakeMovie("Iron Man", "A billionaire Iron Man builds a suit of armor"),
                createFakeMovie("Spider-Man", "Spider-Man A teenager gets spider powers"),
                createFakeMovie("Thor", "Thor A god is banished to Earth")
        );


        when(movieService.getRandomMoviePage()).thenReturn(fakeMovies);

        // call questionGenerator.generate()
        GeneratedQuestion test = questionGenerator.generate();

        // verify the result has 4 options
        Assertions.assertEquals(4, test.getQuestion().getOptions().size());

        // verify the overview contains "---------"
        Assertions.assertTrue(test.getQuestion().getOverview().contains("---------"));

        // verify the correct answer is not null
        Assertions.assertNotNull(test.getCorrectAnswer());
    }

    @Test
    void shouldThrowExceptionIfLessthanExpected() {

        List<TmdbMovieResult> fakeMovies = Arrays.asList(
                createFakeMovie("Avengers", "Avengers save the world from " + "Avengers" + " villain"),
                createFakeMovie("Hulk", "A Hulk scientist transforms into a monster"),
                createFakeMovie("Iron Man", "A billionaire Iron Man builds a suit of armor")
        );


        when(movieService.getRandomMoviePage()).thenReturn(fakeMovies);

        Assertions.assertThrows(IllegalStateException.class, ()-> questionGenerator.generate());
    }

    @Test
    void shouldContainAnswerinOptions() {

        List<TmdbMovieResult> fakeMovies = Arrays.asList(
                createFakeMovie("Avengers", "Avengers save the world from " + "Avengers" + " villain"),
                createFakeMovie("Hulk", "A Hulk scientist transforms into a monster"),
                createFakeMovie("Iron Man", "A billionaire Iron Man builds a suit of armor"),
                createFakeMovie("Thor", "Thor A god is banished to Earth")
        );


        when(movieService.getRandomMoviePage()).thenReturn(fakeMovies);
        GeneratedQuestion testQuestions = questionGenerator.generate();

        Assertions.assertTrue(testQuestions.getQuestion().getOptions().contains(testQuestions.getCorrectAnswer()));
    }
}