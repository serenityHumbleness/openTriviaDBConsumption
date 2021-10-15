package com.devprojects.consumeopentriviadb.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Question {
    private UUID questionId;
    private String question;
    private String answer;
    private List<String> incorrectAnswers;
    private List<String> answerChoices;

    public Question(String question, String answer, List<String> incorrectAnswers) {
        this.question = question;
        this.answer = answer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId() {
        this.questionId = UUID.randomUUID();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<String> incorrectAnswers, String correctAnswer) {
        this.answerChoices = new ArrayList<>();
        this.answerChoices.addAll(incorrectAnswers);
        this.answerChoices.add(correctAnswer);
        Collections.shuffle(this.answerChoices);
    }
}
