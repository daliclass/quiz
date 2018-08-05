package com.winnowsoltutions.quiz;

import com.winnowsolutions.quiz.game.start.CreateNewGame;
import com.winnowsolutions.quiz.QuizController;
import com.winnowsolutions.quiz.game.turn.AnswerQuestion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


public class QuizControllerTest {

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
        QuizController quizController = new QuizController(createNewGame, answerQuestion);
        quizController.newGame();

        verify(createNewGame).startGame(10);
    }

    @Test
    public void whenAPlayerAnswersAQuestionThenCallTheAnswerQuestionUseCase() {
        QuizController quizController = new QuizController(createNewGame, answerQuestion);
        final String gameGuid = "game-guid";
        final String answer = "answer";
        quizController.answerQuestion(gameGuid, answer);

        verify(answerQuestion).answer(gameGuid, answer);
    }
}
