package com.devprojects.consumeopentriviadb.models;

import java.io.Serializable;
import java.util.List;

public class QuestionsFromAPI implements Serializable {

    private int responseCode;
    private List<Result> results;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
