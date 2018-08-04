package com.winnowsolutions.quiz.game;

public interface GameRepository {
    void createGame(Game game);
    Game getGame(String gameGuid);
    void updateGame(Game game);
}
