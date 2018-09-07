package com.winnowsolutions.quiz.repository;

import java.util.UUID;

public interface GameRepository {
    void createGame(Game game);
    Game getGame(UUID gameUUID);
    void updateGame(Game game);
}
