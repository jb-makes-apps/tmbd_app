package com.movie.proj.trivia;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


@Service
public class TriviaService {
    private final QuestionGenerator questionGenerator;
    private final Cache triviaCache;

    public TriviaService(QuestionGenerator questionGenerator, CacheManager cacheManager) {
        this.questionGenerator = questionGenerator;
        this.triviaCache = cacheManager.getCache("trivia");
    }

    public TriviaQuestion getQuestion(){
        GeneratedQuestion generatedQuestion = questionGenerator.generate();
        triviaCache.put(generatedQuestion.getQuestion().getQuestionId(), generatedQuestion.getCorrectAnswer());
        return generatedQuestion.getQuestion();
    }

    @CacheEvict(value = "trivia", key = "#triviaAnswerRequest.questionId")
    public TriviaAnswerResult checkAnswer(TriviaAnswerRequest triviaAnswerRequest){
        String correctAnswer = triviaCache.get(triviaAnswerRequest.getQuestionId(), String.class);
        boolean isCorrect = triviaAnswerRequest.getSelectedTitle().equals(correctAnswer);
        return new TriviaAnswerResult(isCorrect, correctAnswer);
    }

}
