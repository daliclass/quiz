package com.winnowsoltutions.quiz.repository;

import com.winnowsoltutions.quiz.game.common.GameTestHelper;
import com.winnowsolutions.quiz.repository.Game;
import com.winnowsolutions.quiz.repository.GameRepository;
import com.winnowsolutions.quiz.repository.InMemoryGameRepository;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class InMemoryGameRepositoryTest {

    @Test
    public void givenAGameIsCreatedWhenAUserRequestsAGameByIdThenReturnTheAGameRecord() {
        Game expectedGame = GameTestHelper.createCompletedGame();
        UUID gameUuid = expectedGame.getgameUUID();
        GameRepository gameRepository = new InMemoryGameRepository();
        gameRepository.createGame(expectedGame);

        assertEquals(expectedGame, gameRepository.getGame(gameUuid));
    }

    @Test
    public void givenAGameIsCreatedWhenAUserUpdatesAndRequestsTheGameByIdThenTheUpdatedGameIsProvided() {
        Game updatedGame = GameTestHelper.createIncompleteGame();
        Game expectedGame = GameTestHelper.createCompletedGame(updatedGame.getgameUUID());
        UUID gameUuid = expectedGame.getgameUUID();
        GameRepository gameRepository = new InMemoryGameRepository();
        gameRepository.createGame(updatedGame);
        gameRepository.updateGame(expectedGame);

        assertEquals(expectedGame, gameRepository.getGame(gameUuid));
    }
}
