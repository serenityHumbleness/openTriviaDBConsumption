package com.devprojects.consumeopentriviadb.utilities;

import com.devprojects.consumeopentriviadb.models.QuestionsFromAPI;
import com.devprojects.consumeopentriviadb.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MockQuestionsFromAPIUtils {

    private MockQuestionsFromAPIUtils() { }

    public QuestionsFromAPI getMockQuestionsFromAPI() {
        int randomResponseCode = generate(0, 4);

        QuestionsFromAPI questions = new QuestionsFromAPI();
        questions.setResponseCode(randomResponseCode);
        questions.setResults(getMockResults());

        return questions;
    }

    private List<Result> getMockResults() {
        return new ArrayList<Result>() {
            {
                add(getMockResult());
                add(getMockResult());
                add(getMockResult());
                add(getMockResult());
                add(getMockResult());
            }
        };
    }

    private Result getMockResult() {
        List<String> incorrectAnswers = new ArrayList<>() {
            {
                add(String.valueOf(generate(1, 100)));
                add(String.valueOf(generate(1, 100)));
                add(String.valueOf(generate(1, 100)));
                add(String.valueOf(generate(1, 100)));
            }
        };

        Result result = new Result();
        result.setCategory(String.valueOf(generate(1, 100)));
        result.setType(String.valueOf(generate(1, 100)));
        result.setDifficulty(String.valueOf(generate(1, 100)));
        result.setQuestion(String.valueOf(generate(1, 100)));
        result.setCorrect_answer(String.valueOf(generate(1, 100)));
        result.setIncorrect_answers(incorrectAnswers);

        return result;
    }

    private int generate(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
