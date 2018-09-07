package com.winnowsolutions.quiz.game.summary;

import com.winnowsolutions.quiz.game.common.ConvertGameToGameEntity;
import com.winnowsolutions.quiz.repository.Game;
import com.winnowsolutions.quiz.game.entities.GameEntity;
import com.winnowsolutions.quiz.repository.GameRepository;

import java.util.UUID;
import java.util.function.Function;

public class CalculateSummary {

    private final GameRepository gameRepository;
    private final Function<Game, GameEntity> convertGameToGameEntity;

    public CalculateSummary(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.convertGameToGameEntity = new ConvertGameToGameEntity();
    }

    public GameSummary forgameUUID(UUID gameUUID) {
        Game game = gameRepository.getGame(gameUUID);
        GameEntity gameEntity = convertGameToGameEntity.apply(game);
        return new GameSummary(gameUUID, gameEntity.getScore(), gameEntity.getNumberOfQuestions());
    }

    public static class GameSummary {

        private final Double score;
        private final UUID gameUUID;
        private final Integer maxScore;

        public GameSummary(UUID gameUUID, Double score, Integer maxScore) {
            this.gameUUID = gameUUID;
            this.score = score;
            this.maxScore = maxScore;
        }

        public Double getScore() {
            return score;
        }

        public Integer getMaxScore() {
            return maxScore;
        }

        @Override
        public boolean equals(Object o) {
            GameSummary gameSummary = (GameSummary) o;
            return  score.equals(gameSummary.score) &&
                    gameUUID.equals(gameSummary.gameUUID) &&
                    maxScore.equals(gameSummary.maxScore);
        }
    }
}
