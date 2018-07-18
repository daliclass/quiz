package com.winnowsolutions.quiz.game.turn;

import java.util.List;

public class Turn {
    public String quote;
    public String gameGuid;
    public List<String> potentialAnswers;
    public Integer questionNumber;
    public Integer numberOfQuestions;

    @Override
    public boolean equals(Object o) {
        Turn turn = (Turn) o;
        if (
                turn.gameGuid.equals(gameGuid) &&
                turn.questionNumber.equals(questionNumber) &&
                turn.numberOfQuestions.equals(numberOfQuestions) &&
                turn.potentialAnswers.equals(potentialAnswers) &&
                turn.quote.equals(quote)) return true;

        return false;
    }
}
