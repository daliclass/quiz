package com.winnowsoltutions.quiz.summary;

import com.winnowsolutions.quiz.game.ConvertGameToGameEntity;
import com.winnowsolutions.quiz.game.Game;
import com.winnowsolutions.quiz.game.GameEntity;
import com.winnowsolutions.quiz.game.GameRepository;

import java.util.UUID;
import java.util.function.Function;

public class CalculateSummary {

    private final GameRepository gameRepository;
    private final Function<Game, GameEntity> convertGameToGameEntity;

    public CalculateSummary(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.convertGameToGameEntity = new ConvertGameToGameEntity();
    }

    public GameSummary forGameGuid(UUID gameGuid) {
        Game game = gameRepository.getGame(gameGuid.toString());
        GameEntity gameEntity = convertGameToGameEntity.apply(game);
        return new GameSummary(gameGuid, gameEntity.getScore(), gameEntity.getNumberOfQuestions());
    }

    public static class GameSummary {

        private final Double score;
        private final UUID gameGuid;
        private final Integer maxScore;

        public GameSummary(UUID gameGuid, Double score, Integer maxScore) {
            this.gameGuid = gameGuid;
            this.score = score;
            this.maxScore = maxScore;
        }

        @Override
        public boolean equals(Object o) {
            GameSummary gameSummary = (GameSummary) o;
            return  score.equals(gameSummary.score) &&
                    gameGuid.equals(gameSummary.gameGuid) &&
                    maxScore.equals(gameSummary.maxScore);
        }
    }
}
