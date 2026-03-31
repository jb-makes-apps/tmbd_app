package com.movie.proj.serviceTest;

import com.movie.proj.domain.MovieService;
import com.movie.proj.tmdb.TmdbMovieResult;
import com.movie.proj.trivia.GeneratedQuestion;
import com.movie.proj.trivia.QuestionGenerator;
import com.movie.proj.trivia.TriviaQuestion;
import com.movie.proj.trivia.TriviaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TriviaServiceTest {

    @Mock
    private QuestionGenerator questionGenerator;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache triviaCache;

    private TriviaService triviaService;

    @BeforeEach
    void setUp(){
        when(cacheManager.getCache("trivia")).thenReturn(triviaCache);
        triviaService = new TriviaService(questionGenerator, cacheManager);
    }

    @Test
    void shouldReturnCachedAnswer() {
        TriviaQuestion question = new TriviaQuestion("123", "--------- saves the world",
                List.of("Avengers", "Hulk", "Iron Man", "Thor"));
        GeneratedQuestion fakeGenerated = new GeneratedQuestion(question, "Avengers");

        // Tell the mock what to return
        when(questionGenerator.generate()).thenReturn(fakeGenerated);

        TriviaQuestion result = triviaService.getQuestion();

        // checks the returned value
        Assertions.assertEquals("123", result.getQuestionId());

        // checks that the cache was actually called with the right arguments
        verify(triviaCache).put("123", "Avengers");

    }

}
