package com.winnowsolutions.quiz.game;

public interface GameRepository {
    void createGame(Game gameEntity);
    Game getGame(String gameGuid);
}
