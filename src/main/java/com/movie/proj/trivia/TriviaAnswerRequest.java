package com.movie.proj.trivia;

public class TriviaAnswerRequest {

    private String questionId;
    private String selectedTitle;

    public TriviaAnswerRequest() {
    }

    public TriviaAnswerRequest(String selectedTitle, String questionId) {
        this.selectedTitle = selectedTitle;
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getSelectedTitle() {
        return selectedTitle;
    }

    public void setSelectedTitle(String selectedTitle) {
        this.selectedTitle = selectedTitle;
    }

}
