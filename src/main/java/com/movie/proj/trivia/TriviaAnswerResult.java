package com.movie.proj.trivia;

public class TriviaAnswerResult {
    private boolean correct;
    private String correctAnswer;

    public TriviaAnswerResult(boolean correct, String correctAnswer) {
        this.correct = correct;
        this.correctAnswer = correctAnswer;
    }

    public TriviaAnswerResult() {
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
