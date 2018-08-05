package com.winnowsoltutions.quiz.game.turn;

import com.winnowsolutions.quiz.repository.Game;
import com.winnowsolutions.quiz.repository.GameRepository;
import com.winnowsolutions.quiz.game.turn.AnswerQuestion;
import com.winnowsolutions.quiz.game.turn.Turn;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AnswerQuestionTest {

    final UUID GAME_GUID = UUID.randomUUID();
    final String ANSWER = "ANSWER";
    final String ANSWER_QUESTION_TWO = "ANSWER_2";
    final String QUOTE = "Look what you made me do";
    Map<Integer, List<String>> uninitialisedQuestionAnswers = new HashMap() {{
        put(0, new ArrayList());
        put(1, new ArrayList());
    }};

    Map<Integer, List<String>> expectedQuestionAnswers = new HashMap() {{
        put(0, new ArrayList() {{
            add(ANSWER);
        }});
        put(1, new ArrayList());
    }};

    List<String> quotes = new ArrayList() {{add(QUOTE);}};

    List<String> incorrectAnswers = new ArrayList();

    @Mock
    GameRepository gameRepository;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void whenUserIsAnsweringAQuestionAndTheyAreNotOnTheLastQuestionThenProvideThemTheNextTurn() {
        Integer questionNumber = 0;
        Integer quoteNumber = 0;
        List<Game.Question> questions = new ArrayList(){{
            add(new Game.Question(ANSWER, quotes, incorrectAnswers));
            add(new Game.Question(ANSWER_QUESTION_TWO, quotes, incorrectAnswers));
        }};

        Game game = new Game(questions, GAME_GUID, questionNumber, quoteNumber, uninitialisedQuestionAnswers);
        when(gameRepository.getGame(GAME_GUID)).thenReturn(game);
        AnswerQuestion answerQuestion = new AnswerQuestion(gameRepository);

        Turn turn = answerQuestion.answer(GAME_GUID.toString(), ANSWER);
        assertEquals(expectedTurn(), turn);
    }

    @Test
    public void whenUserIsAnsweringTheLastQuestionThenProvideThemWithABlankTurn() {
        Integer questionNumber = 2;
        Integer quoteNumber = 0;
        List<Game.Question> questions = new ArrayList(){{
            add(new Game.Question(ANSWER, quotes, incorrectAnswers));
            add(new Game.Question(ANSWER_QUESTION_TWO, quotes, incorrectAnswers));
        }};

        Game game = new Game(questions, GAME_GUID, questionNumber, quoteNumber, uninitialisedQuestionAnswers);
        when(gameRepository.getGame(GAME_GUID)).thenReturn(game);
        AnswerQuestion answerQuestion = new AnswerQuestion(gameRepository);

        Turn turn = answerQuestion.answer(GAME_GUID.toString(), ANSWER);
        assertEquals(blankTurn(), turn);
    }

    @Test
    public void whenUserIsAnsweringAQuestionThenSaveTheGame() {
        Integer questionNumber = 0;
        Integer quoteNumber = 0;
        List<Game.Question> questions = new ArrayList(){{
            add(new Game.Question(ANSWER, quotes, incorrectAnswers));
            add(new Game.Question(ANSWER_QUESTION_TWO, quotes, incorrectAnswers));
        }};

        Game game = new Game(
                questions,
                GAME_GUID,
                questionNumber,
                quoteNumber,
                uninitialisedQuestionAnswers);

        Game expectedGame = new Game(
                questions,
                GAME_GUID,
                questionNumber + 1,
                quoteNumber,
                expectedQuestionAnswers);

        when(gameRepository.getGame(GAME_GUID)).thenReturn(game);
        AnswerQuestion answerQuestion = new AnswerQuestion(gameRepository);

        answerQuestion.answer(GAME_GUID.toString(), ANSWER);
        verify(gameRepository).updateGame(expectedGame);
    }

    private Turn expectedTurn() {
        Turn expectedTurn = new Turn();
        expectedTurn.quote = QUOTE;
        expectedTurn.potentialAnswers = new ArrayList() {{add(ANSWER_QUESTION_TWO);}};
        expectedTurn.numberOfQuestions = 2;
        expectedTurn.questionNumber = 1;
        expectedTurn.gameGuid = GAME_GUID.toString();
        return expectedTurn;
    }

    private Turn blankTurn() {
        Turn expectedTurn = new Turn();
        expectedTurn.quote = "";
        expectedTurn.potentialAnswers = new ArrayList();
        expectedTurn.numberOfQuestions = 2;
        expectedTurn.questionNumber = 2;
        expectedTurn.gameGuid = GAME_GUID.toString();
        return expectedTurn;
    }
}
