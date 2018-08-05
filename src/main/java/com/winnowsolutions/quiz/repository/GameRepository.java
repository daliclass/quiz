package com.winnowsolutions.quiz.repository;

import java.util.UUID;

public interface GameRepository {
    void createGame(Game game);
    Game getGame(UUID gameGuid);
    void updateGame(Game game);
}
