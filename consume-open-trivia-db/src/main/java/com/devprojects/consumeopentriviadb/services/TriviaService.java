package com.devprojects.consumeopentriviadb.services;

import com.devprojects.consumeopentriviadb.models.QuestionsFromAPI;
import com.devprojects.consumeopentriviadb.models.Result;
import com.devprojects.consumeopentriviadb.views.QuestionResponse;
import com.devprojects.consumeopentriviadb.views.QuestionIdAnswerResponse;
import com.devprojects.consumeopentriviadb.models.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TriviaService {

    public List<QuestionResponse> getMultipleChoiceResponse(List<Question> questionRespons) {

        return questionRespons.stream()
                .map(question -> new QuestionResponse(question.getQuestionId(),
                        question.getQuestion(), question.getAnswerChoices()))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionResponses(QuestionsFromAPI questions) {
        List<Result> results = questions.getResults();

        List<Question> questionRespons = results.stream()
                .map(result -> new Question(result.getQuestion(), result.getCorrect_answer(), result.getIncorrect_answers()))
                .collect(Collectors.toList());

        questionRespons.forEach(question -> question
                .setAnswerChoices(question.getIncorrectAnswers(), question.getAnswer()));
        questionRespons.forEach(Question::setQuestionId);

        return questionRespons;
    }

    public List<QuestionIdAnswerResponse> getCorrectAnswers(List<Question> questionRespons) {
        return questionRespons.stream()
                .map(question -> new QuestionIdAnswerResponse(question.getQuestionId(), question.getAnswer()))
                .collect(Collectors.toList());
    }
}
