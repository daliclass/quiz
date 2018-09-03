package com.winnowsoltutions.quiz.game.summary;

import com.winnowsoltutions.quiz.game.common.GameTestHelper;
import com.winnowsolutions.quiz.repository.Game;
import com.winnowsolutions.quiz.repository.GameRepository;
import com.winnowsolutions.quiz.game.summary.CalculateSummary;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
        Game completedGame = GameTestHelper.createCompletedGame();
        when(gameRepository.getGame(completedGame.getGameGuid())).thenReturn(completedGame);
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

    @Test
    public void givenAUserHasNotCompletedAGameWhenRequestingTheGameSummaryThenGetTheStateOfThereCurrentGame() {
        Game incompleteGame = GameTestHelper.createIncompleteGame();
        when(gameRepository.getGame(incompleteGame.getGameGuid())).thenReturn(incompleteGame);
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
}
