package com.movie.proj.trivia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trivia")
public class TriviaController {

    private final QuestionGenerator questionGenerator;

    public TriviaController(QuestionGenerator questionGenerator) { this.questionGenerator = questionGenerator; }


    @GetMapping("/getQuestion")
        public GeneratedQuestion generateQuestion() {
        return questionGenerator.generate();
    }
}
