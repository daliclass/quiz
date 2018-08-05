package com.winnowsolutions.quiz.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryGameRepository implements GameRepository {

    private final Map<UUID, Game> gameStore;

    public InMemoryGameRepository() {
        this.gameStore = new HashMap();
    }

    @Override
    public void createGame(Game game) {
        gameStore.put(game.getGameGuid(), game);
    }

    @Override
    public Game getGame(UUID gameGuid) {
        return gameStore.get(gameGuid);
    }

    @Override
    public void updateGame(Game game) {
        gameStore.put(game.getGameGuid(), game);
    }
}
