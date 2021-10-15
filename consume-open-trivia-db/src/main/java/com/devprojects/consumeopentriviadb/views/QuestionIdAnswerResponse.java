package com.devprojects.consumeopentriviadb.views;

import java.util.UUID;

public class QuestionIdAnswerResponse {
    private UUID questionId;
    private String answer;

    public QuestionIdAnswerResponse(UUID questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }
}
