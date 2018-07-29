package com.winnowsoltutions.quiz.game.start;

import com.winnowsolutions.quiz.game.start.CreateNewGame;
import com.winnowsolutions.quiz.game.Game;
import com.winnowsolutions.quiz.game.GameRepository;
import com.winnowsolutions.quiz.game.turn.Turn;
import com.winnowsolutions.quiz.question.Question;
import com.winnowsolutions.quiz.question.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateNewGameTestTest {

    @Mock
    QuestionService questionService;

    @Mock
    GameRepository gameRepository;

    private final String CELEBRITY_NAME = "Justin Beiber";
    private final List<String> QUOTES = new ArrayList() {{add("What do you mean?");}};
    private final List<String> EMPTY_ANSWERS = new ArrayList();
    private final Integer NUMBER_OF_QUESTIONS = 10;

    @Before
    public void before() {
       initMocks(this);
    }

    @Test
    public void whenCreatingANewGameThenTheFirstTurnIsReadyForTheUserToPlay() {
        final List<Question> questions = getQuestions();
        List<String> celebrityNames = new ArrayList<String>() {{
           add("Mila Kunis");
           add("Jenifer Lawrence");
           add("Martin Luther King");
        }};

        when(questionService.getQuestions(NUMBER_OF_QUESTIONS)).thenReturn(questions);
        when(questionService.getCelebrityNames(3)).thenReturn(celebrityNames);

        CreateNewGame createNewGame = new CreateNewGame(questionService, gameRepository);

        Turn expectedTurn = new Turn();
        expectedTurn.gameGuid = "";
        expectedTurn.numberOfQuestions = NUMBER_OF_QUESTIONS;
        expectedTurn.questionNumber = 0;
        expectedTurn.quote = questions.get(0).getQuotes().get(0);
        expectedTurn.potentialAnswers = new ArrayList<String>(celebrityNames){{
            add(questions.get(0).getCelebrityName());
        }};

        validateTurn(expectedTurn, createNewGame.startGame(NUMBER_OF_QUESTIONS));
    }

    private void validateTurn(Turn expectedTurn, Turn actualTurn) {
        assertTrue("GameGuid is not null" ,actualTurn.gameGuid != null);
        assertEquals(expectedTurn.numberOfQuestions, actualTurn.numberOfQuestions);
        assertEquals(expectedTurn.questionNumber, actualTurn.questionNumber);
        assertEquals(expectedTurn.quote, actualTurn.quote);
        assertTrue("Potential answers are populated including the answer",
                expectedTurn.potentialAnswers.containsAll(actualTurn.potentialAnswers));
    }

    private List<Question> getQuestions() {
        List<Question> questions = new ArrayList<Question>();

        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            questions.add(new Question(CELEBRITY_NAME + i, QUOTES));
        }
        return questions;
    }

    private Game getGame() {
        List<Game.Question> gameQuestions = new ArrayList();

        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
            gameQuestions.add(new Game.Question(CELEBRITY_NAME + i, QUOTES, EMPTY_ANSWERS));
        }

        Game game = new Game(gameQuestions, UUID.randomUUID(), 0, 0);

        return game;
    }

    @Test
    public void whenCreatingANewGameThenStoreTheNewGameInTheQuestionService() {
        List<Question> questions = getQuestions();
        when(questionService.getQuestions(NUMBER_OF_QUESTIONS)).thenReturn(questions);

        CreateNewGame createNewGame = new CreateNewGame(questionService, gameRepository);
        createNewGame.startGame(NUMBER_OF_QUESTIONS);

        ArgumentCaptor<Game> captor = ArgumentCaptor.forClass(Game.class);

        verify(gameRepository).createGame(captor.capture());
        Game expectedGame = getGame();
        Game actualGame = captor.getValue();

        assertEquals(expectedGame.getQuestions(), actualGame.getQuestions());
        assertEquals(expectedGame.getQuestionNumber(), actualGame.getQuoteNumber());
        assertEquals(expectedGame.getQuoteNumber(), actualGame.getQuoteNumber());
        assertTrue("GameGuid is not null" ,actualGame.getGameGuid() != null);
    }
}
