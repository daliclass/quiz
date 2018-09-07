package com.winnowsolutions.quiz.game.turn;

import java.util.List;
import java.util.UUID;

public class Turn {
    public String quote;
    public UUID gameUUID;
    public List<String> potentialAnswers;
    public Integer questionNumber;
    public Integer numberOfQuestions;

    @Override
    public boolean equals(Object o) {
        Turn turn = (Turn) o;
        if (
                turn.gameUUID.equals(gameUUID) &&
                turn.questionNumber.equals(questionNumber) &&
                turn.numberOfQuestions.equals(numberOfQuestions) &&
                turn.potentialAnswers.equals(potentialAnswers) &&
                turn.quote.equals(quote)) return true;

        return false;
    }
}
