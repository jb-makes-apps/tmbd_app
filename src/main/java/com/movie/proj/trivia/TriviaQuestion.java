package com.movie.proj.trivia;

import java.util.List;

public class TriviaQuestion {

    private String questionId;
    private List<String> options;
    private String overview;

    public TriviaQuestion(String questionId, String overview, List<String> options) {
        this.questionId = questionId;
        this.overview = overview;
        this.options = options;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

}
