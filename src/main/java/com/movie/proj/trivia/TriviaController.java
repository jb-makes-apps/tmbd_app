package com.movie.proj.trivia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trivia")
public class TriviaController {

    private final QuestionGenerator questionGenerator;

    public TriviaController(QuestionGenerator questionGenerator) { this.questionGenerator = questionGenerator; }

    @GetMapping("/getQuestion")
        public TriviaQuestion generateQuestion() {
        return questionGenerator.generate();
    }
}
