package engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
public class QuizController {
    private static final String NOT_FOUND_MESSAGE = "The quiz of this id hasn't been found. Maybe it doesn't exist?";

    private List<QuizQuestion> questions;

    private QuizQuestion quizQuestion;

    @Autowired
    private QuizQuestionsRepository quizQuestionsRepository;
//    private int question_id = 0;

    public QuizController() {
        questions = new ArrayList<>();
        quizQuestion = new QuizQuestion();
    }

    @GetMapping("/api/quiz")
    public QuizQuestion getQuestion() {
        return quizQuestion;
    }

    @GetMapping("/api/quizzes/{id}")
    public QuizQuestion getSpecificIdQuiz(@PathVariable(value = "id") final int id) {
        return quizQuestionsRepository.findById(id).get();
    }

    @GetMapping("/api/quizzes")
    public List<QuizQuestion> getAllQuizzes() {
//        return questions;
        List<QuizQuestion> quizzes = new ArrayList<>();
        for(QuizQuestion quizQuestion : quizQuestionsRepository.findAll()) {
            quizzes.add(quizQuestion);
        }
        return quizzes;
    }

    @PostMapping(path = "/api/quizzes", consumes = "application/json")
    public QuizQuestion createQuiz(@Valid @RequestBody QuizQuestion quizQuestion) {
//        questions.add(new QuizQuestion(question_id++,
//                quizQuestion.getTitle(),
//                quizQuestion.getText(),
//                quizQuestion.getOptions(),
//                quizQuestion.getAnswer()));
        QuizQuestion questionToAdd = new QuizQuestion(quizQuestion.getTitle(),
                quizQuestion.getText(),
                quizQuestion.getOptions(),
                quizQuestion.getAnswer());
        quizQuestionsRepository.save(questionToAdd);
//        return questions.get(questions.size() - 1);
        return questionToAdd;
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public ServerResponse solveSpecificIdQuiz(@PathVariable(value = "id") final int id,
                                              @RequestBody QuestionAnswer questionAnswer) {
        ServerResponse serverResponse = new ServerResponse(false, "Wrong answer! Please, try again.");
        QuizQuestion specificIdQuestion = quizQuestionsRepository.findById(id).get();
        if(checkAnswers(specificIdQuestion.getAnswer(), questionAnswer.getAnswer())) {
            serverResponse.setSuccess(true);
            serverResponse.setFeedback("Congratulations, you're right!");
        }
        return serverResponse;
    }

//    @PostMapping(path = "/api/quiz")
//    public ServerResponse checkAnswer(QuestionAnswer questionAnswer) {
//        System.out.println(questionAnswer.getAnswer());
//        ServerResponse serverResponse = new ServerResponse(false, "Wrong answer! Please, try again.");
//        if(questionAnswer.getAnswer() == 2) {
//            serverResponse.setSuccess(true);
//            serverResponse.setFeedback("Congratulations, you're right!");
//        }
//        return serverResponse;
//    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NOT_FOUND_MESSAGE)
    public HashMap<String, String> handleIndexOutOfBoundsException(Exception e) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND_MESSAGE);
        response.put("error", e.getClass().getSimpleName());
        return response;
    }

    private boolean checkAnswers(int[] rightAnswers, int[] answerToValidate) {
        Arrays.sort(answerToValidate);
        if(Arrays.equals(rightAnswers, answerToValidate)) {
            return true;
        } else {
            return false;
        }
    }
}
