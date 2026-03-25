package com.movie.proj.trivia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


@Service
public class TriviaService {
    private final QuestionGenerator questionGenerator;
    private final Cache triviaCache;
    private static final Logger logger = LoggerFactory.getLogger(TriviaService.class);

    public TriviaService(QuestionGenerator questionGenerator, CacheManager cacheManager) {
        this.questionGenerator = questionGenerator;
        this.triviaCache = cacheManager.getCache("trivia");
    }

    public TriviaQuestion getQuestion(){
        long startTime = System.currentTimeMillis();

        try {
            GeneratedQuestion generatedQuestion = questionGenerator.generate();
            triviaCache.put(generatedQuestion.getQuestion().getQuestionId(), generatedQuestion.getCorrectAnswer());

            long duration = System.currentTimeMillis() - startTime;

            logger.info("Question generated with id {} in {}ms", generatedQuestion.getQuestion().getQuestionId(), duration);

            return generatedQuestion.getQuestion();

        } catch (Exception e) {
            logger.error("Failed to fetch movies from TMDB", e);
            throw e;
        }
    }

    @CacheEvict(value = "trivia", key = "#triviaAnswerRequest.questionId")
    public TriviaAnswerResult checkAnswer(TriviaAnswerRequest triviaAnswerRequest) {
        long startTime = System.currentTimeMillis();

        try {
            String correctAnswer = triviaCache.get(triviaAnswerRequest.getQuestionId(), String.class);
            boolean isCorrect = triviaAnswerRequest.getSelectedTitle().equals(correctAnswer);

            long duration = System.currentTimeMillis() - startTime;

            logger.info("Answer checked for questionId {} - correct: {} in {}ms", triviaAnswerRequest.getQuestionId(), isCorrect, duration);

            return new TriviaAnswerResult(isCorrect, correctAnswer);

        } catch (Exception e) {
            logger.error("Failed to check answer for questionId {}",
                    triviaAnswerRequest.getQuestionId(), e);
            throw e;
        }
    }

}
