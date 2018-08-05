package com.winnowsolutions.quiz.repository;

public interface GameRepository {
    void createGame(Game game);
    Game getGame(String gameGuid);
    void updateGame(Game game);
}
