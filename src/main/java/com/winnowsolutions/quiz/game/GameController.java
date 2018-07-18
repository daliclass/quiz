package com.winnowsolutions.quiz.game;

import com.winnowsolutions.quiz.game.start.CreateNewGame;
import com.winnowsolutions.quiz.game.turn.AnswerQuestion;
import com.winnowsolutions.quiz.game.turn.Turn;

public class GameController {
    private final static Integer NUMBER_OF_QUESTIONS = 10;
    private final CreateNewGame createNewGame;

    public GameController(CreateNewGame createNewGame, AnswerQuestion answerQuestion) {
        this.createNewGame = createNewGame;
    }

    public Turn newGame() {
        return createNewGame.startGame(NUMBER_OF_QUESTIONS);
    }

    public void answerQuestion(String s, String answer) {
    }
}
