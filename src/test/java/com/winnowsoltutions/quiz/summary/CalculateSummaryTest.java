package com.winnowsoltutions.quiz.summary;

import com.winnowsolutions.quiz.game.Game;
import com.winnowsolutions.quiz.game.GameRepository;
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

    public void givenAUserHasNotCompletedAGameWhenRequestingTheGameSummaryThenReturnAErrorValue() {
    }
}
