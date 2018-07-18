package com.winnowsolutions.quiz.game;

import java.util.List;
import java.util.UUID;

public class Game {
    private final List<Question> questions;
    private final UUID gameGuid;
    private final Integer questionNumber;
    private final Integer quoteNumber;

    public Game(List<Question> questions, UUID gameGuid, Integer questionNumber, Integer quoteNumber) {
        this.questions = questions;
        this.gameGuid = gameGuid;
        this.questionNumber = questionNumber;
        this.quoteNumber = quoteNumber;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public UUID getGameGuid() {
        return gameGuid;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public Integer getQuoteNumber() {
        return quoteNumber;
    }

    public static class Question {
        private final String celebrityName;
        private final List<String> quotes;
        private final List<String> incorrectAnswers;

        public Question(String celebrityName, List<String> quotes, List<String> incorrectAnswers) {
            this.celebrityName = celebrityName;
            this.quotes = quotes;
            this.incorrectAnswers = incorrectAnswers;
        }

        public List<String> getIncorrectAnswers() {
            return incorrectAnswers;
        }

        public String getCelebrityName() {
            return celebrityName;
        }

        public List<String> getQuotes() {
            return quotes;
        }

        @Override
        public boolean equals(Object o) {
            Question question = (Question) o;
            if (question.celebrityName.equals(celebrityName) &&
                    question.incorrectAnswers.equals(incorrectAnswers) && question.quotes.equals(quotes)) return true;

            return false;
        }
    }
}
