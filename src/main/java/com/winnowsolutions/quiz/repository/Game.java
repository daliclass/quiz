package com.winnowsolutions.quiz.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Game {
    private final List<Question> questions;
    private final UUID gameUUID;
    private final Integer questionNumber;
    private final Integer quoteNumber;
    private Map<Integer, List<String>> questionAnswers;

    public Game(List<Question> questions,
                UUID gameUUID,
                Integer questionNumber,
                Integer quoteNumber,
                Map<Integer, List<String>> questionAnswers) {
        this.questions = questions;
        this.gameUUID = gameUUID;
        this.questionNumber = questionNumber;
        this.quoteNumber = quoteNumber;
        this.questionAnswers = questionAnswers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public UUID getgameUUID() {
        return gameUUID;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public Integer getQuoteNumber() {
        return quoteNumber;
    }

    public Map<Integer, List<String>> getQuestionAnswers() {
        return questionAnswers;
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

    @Override
    public boolean equals(Object o) {
        Game game = (Game) o;
        if (questions.equals(game.questions) &&
                gameUUID.equals(game.gameUUID) &&
                questionNumber.equals(game.questionNumber) &&
                quoteNumber.equals(game.quoteNumber) &&
                questionAnswers.equals(game.questionAnswers))
            return true;
        return false;
    }
}
