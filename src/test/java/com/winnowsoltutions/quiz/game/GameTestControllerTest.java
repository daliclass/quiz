package com.winnowsoltutions.quiz.game;

import com.winnowsolutions.quiz.game.start.CreateNewGame;
import com.winnowsolutions.quiz.game.GameController;
import com.winnowsolutions.quiz.game.turn.AnswerQuestion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


public class GameTestControllerTest {

    @Mock
    CreateNewGame createNewGame;

    @Mock
    AnswerQuestion answerQuestion;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void whenANewGameIsRequestedThenTheNewGameUseCaseIsCalled() {
        GameController gameController = new GameController(createNewGame, answerQuestion);
        gameController.newGame();

        verify(createNewGame).startGame(10);
    }

    @Test
    public void whenAPlayerAnswersAQuestionThenCallTheAnswerQuestionUseCase() {
        GameController gameController = new GameController(createNewGame, answerQuestion);
        final String gameGuid = "game-guid";
        final String answer = "answer";
        gameController.answerQuestion(gameGuid, answer);

        verify(answerQuestion).answer(gameGuid, answer);
    }
}
