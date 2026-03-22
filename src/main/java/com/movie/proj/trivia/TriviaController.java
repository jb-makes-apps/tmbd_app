package com.movie.proj.trivia;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/trivia")
public class TriviaController {

    private final TriviaService triviaService;

    public TriviaController(TriviaService triviaService) { this.triviaService = triviaService; }


    @GetMapping("/question")
        public TriviaQuestion triviaQuestion() {
        return triviaService.getQuestion();
    }

    @PostMapping("/answer")
    public TriviaAnswerResult checkAnswer(@RequestBody TriviaAnswerRequest request) {
        return triviaService.checkAnswer(request);

    }
}
