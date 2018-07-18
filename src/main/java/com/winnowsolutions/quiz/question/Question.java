package com.winnowsolutions.quiz.question;

import java.util.List;

public class Question {
    private final String celebrityName;
    private final List<String> quotes;

    public Question(String celebrityName, List<String> quotes) {
        this.celebrityName = celebrityName;
        this.quotes = quotes;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public List<String> getQuotes() {
        return quotes;
    }
}