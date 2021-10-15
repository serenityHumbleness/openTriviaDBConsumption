package com.devprojects.consumeopentriviadb.controllers;

import com.devprojects.consumeopentriviadb.models.QuestionsFromAPI;
import com.devprojects.consumeopentriviadb.services.TriviaService;
import com.devprojects.consumeopentriviadb.views.QuestionResponse;
import com.devprojects.consumeopentriviadb.views.QuestionIdAnswerResponse;
import com.devprojects.consumeopentriviadb.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
public class TriviaController {

    /**
     * Next step:
     * 1. Let the user decide how many questions they want.
     */

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TriviaService triviaService;

    private List<QuestionIdAnswerResponse> questionIdAnswerResponses;

    private static String url = "https://opentdb.com/api.php?amount=5&type=multiple";

    @GetMapping("/questions")
    public List<QuestionResponse> getQuestions() throws Exception {
        //check null and response code and throw exception if needed
        QuestionsFromAPI questionsFromAPI = restTemplate.getForObject(url, QuestionsFromAPI.class);
        if(questionsFromAPI == null) {
            throw new NullPointerException("API returned null");
        }

        if(questionsFromAPI.getResponseCode() != 0) {
            checkResponseCode(questionsFromAPI.getResponseCode());
        }

        // serialize JSON to POJO list of QuestionResponse
        List<Question> questionRespons = triviaService.getQuestionResponses(questionsFromAPI);

        // save questionIds and correct answers
        questionIdAnswerResponses = triviaService.getCorrectAnswers(questionRespons);

        // only questionIds, questions with possible answers
        return triviaService.getMultipleChoiceResponse(questionRespons);
    }

    private void checkResponseCode(int responseCode) throws Exception {
        switch (responseCode) {
            case 1: throw new Exception("No Results! Could not return results. The API doesn't have enough questions for your query. (Ex. Asking for 50 Questions in a Category that only has 20.)");
            case 2: throw new Exception("Invalid Parameter! Contains an invalid parameter. Arguements passed in aren't valid. (Ex. Amount = Five)");
            case 3: throw new Exception("Token Not Found! Session Token does not exist.");
            case 4: throw new Exception("Token Empty! Session Token has returned all possible questions for the specified query. Resetting the Token is necessary.");
        }
    }

    @PostMapping("/questions/{questionid}/answer")
    public String getQuestionIdAnswerResponse(@PathVariable(value="questionid") UUID questionId) throws Exception {

        return questionIdAnswerResponses.stream()
                .filter(questionIdAnswerResponse -> questionId.equals(questionIdAnswerResponse.getQuestionId()))
                .findFirst()
                .orElseThrow(Exception::new)
                .getAnswer();
    }
}
