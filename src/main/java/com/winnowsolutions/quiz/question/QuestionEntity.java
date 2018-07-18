package com.winnowsolutions.quiz.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionEntity {
    private final String celebrityName;
    private final List<String> quotes;
    private final List<String> incorrectAnswers;

    public QuestionEntity(String celebrityName, List<String> quotes, List<String> incorrectAnswers) {
        this.celebrityName = celebrityName;
        this.quotes = quotes;
        this.incorrectAnswers = incorrectAnswers;
    }

    public List<String> getPotentialAnswers() {
        List<String> potentialAnswers = new ArrayList<String>(incorrectAnswers) {{add(celebrityName);}};
        Collections.shuffle(potentialAnswers);
        return potentialAnswers;
    }

    public List<String> getQuotes() {
        return quotes;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
