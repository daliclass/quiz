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
        gameStore.put(game.getgameUUID(), game);
    }

    @Override
    public Game getGame(UUID gameUUID) {
        return gameStore.get(gameUUID);
    }

    @Override
    public void updateGame(Game game) {
        gameStore.put(game.getgameUUID(), game);
    }
}
