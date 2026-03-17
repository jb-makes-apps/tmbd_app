package com.movie.proj.trivia;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TriviaService {
    private HashMap<String, String> questionMap = new HashMap<>();
    private final QuestionGenerator questionGenerator;

    public TriviaService(QuestionGenerator questionGenerator) {
        this.questionGenerator = questionGenerator;
    }

    public TriviaQuestion getQuestion(){
        GeneratedQuestion tmp = questionGenerator.generate();
        questionMap.put(tmp.getQuestion().getQuestionId(), tmp.getCorrectAnswer());
        return tmp.getQuestion();
    }

    public TriviaAnswerResult checkAnswer(TriviaAnswerRequest triviaAnswerRequest){
        String correctAnswer = questionMap.get(triviaAnswerRequest.getQuestionId());
        boolean isCorrect = triviaAnswerRequest.getSelectedTitle().equals(correctAnswer);
        return new TriviaAnswerResult(isCorrect, correctAnswer);
    }

}
