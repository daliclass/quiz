package com.winnowsoltutions.quiz.game.summary;

import com.winnowsolutions.quiz.repository.Game;
import com.winnowsolutions.quiz.repository.GameRepository;
import com.winnowsolutions.quiz.game.summary.CalculateSummary;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CalculateSummaryTest {

    @Mock
    GameRepository gameRepository;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void givenAUserHasCompletedAGameWhenCreatingTheGameSummaryThenCalculateTheUsersScore() {
        Game completedGame = createCompletedGame();
        when(gameRepository.getGame(completedGame.getGameGuid().toString())).thenReturn(completedGame);
        CalculateSummary calculateSummary = new CalculateSummary(gameRepository);
        CalculateSummary.GameSummary gameSummary = calculateSummary.forGameGuid(completedGame.getGameGuid());
        CalculateSummary.GameSummary expectedGameSummary =
                new CalculateSummary.GameSummary(
                            completedGame.getGameGuid(),
                            4.5,
                            10
                        );

        assertEquals(expectedGameSummary, gameSummary);
    }

    private Game createCompletedGame() {
        String adam = "Adam";
        String jill = "Jill";
        UUID gameUuid = UUID.randomUUID();
        Integer questionNumber = 10;
        Integer quoteNumber = 0;
        List<Game.Question> questions = new ArrayList(){{
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(jill, null, null));
            add(new Game.Question(jill, null, null));
            add(new Game.Question(jill, null, null));
            add(new Game.Question(jill, null, null));
            add(new Game.Question(jill, null, null));
        }};
        Map<Integer, List<String>> questionAnswers = new HashMap() {{
            put(0, new ArrayList(){{ add(jill); add(adam); }});
            put(1, new ArrayList(){{ add(adam); }});
            put(2, new ArrayList(){{ add(adam); }});
            put(3, new ArrayList(){{ add(adam); }});
            put(4, new ArrayList(){{ add(adam); }});
            put(5, new ArrayList(){{ add(adam); }});
            put(6, new ArrayList(){{ add(adam); }});
            put(7, new ArrayList(){{ add(adam); }});
            put(8, new ArrayList(){{ add(adam); }});
            put(9, new ArrayList(){{ add(adam); }});
        }};

        return new Game(questions, gameUuid, questionNumber, quoteNumber, questionAnswers);
    }

    @Test
    public void givenAUserHasNotCompletedAGameWhenRequestingTheGameSummaryThenGetTheStateOfThereCurrentGame() {
        Game incompleteGame = createIncompleteGame();
        when(gameRepository.getGame(incompleteGame.getGameGuid().toString())).thenReturn(incompleteGame);
        CalculateSummary calculateSummary = new CalculateSummary(gameRepository);
        CalculateSummary.GameSummary gameSummary = calculateSummary.forGameGuid(incompleteGame.getGameGuid());
        CalculateSummary.GameSummary expectedGameSummary =
                new CalculateSummary.GameSummary(
                        incompleteGame.getGameGuid(),
                        0.5,
                        3
                );

        assertEquals(expectedGameSummary, gameSummary);
    }

    private Game createIncompleteGame() {
        String adam = "Adam";
        String jill = "Jill";
        UUID gameUuid = UUID.randomUUID();
        Integer questionNumber = 1;
        Integer quoteNumber = 0;
        List<Game.Question> questions = new ArrayList(){{
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
        }};
        Map<Integer, List<String>> questionAnswers = new HashMap() {{
            put(0, new ArrayList(){{ add(jill); add(adam); }});
        }};

        return new Game(questions, gameUuid, questionNumber, quoteNumber, questionAnswers);
    }
}
