package com.winnowsolutions.quiz.question;

import java.util.List;

public class Question {
    private String celebrityName;
    private List<String> quotes;

    public Question() {

    }

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

    public void setCelebrityName(String celebrityName) {
        this.celebrityName = celebrityName;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }

    @Override
    public boolean equals(Object other) {
        Question otherQuestion = (Question) other;
        return this.celebrityName.equals(otherQuestion.celebrityName) && this.quotes.equals(otherQuestion.quotes);
    }
}