package com.movie.proj.trivia;

public class GeneratedQuestion {
    private TriviaQuestion question;
    private String answer;

    public GeneratedQuestion(TriviaQuestion question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public TriviaQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TriviaQuestion triviaQuestion) {
        this.question = triviaQuestion;
    }

    public String getCorrectAnswer() {
        return answer;
    }

    public void setCorrectAnswer(String answer) {
        this.answer = answer;
    }
}
