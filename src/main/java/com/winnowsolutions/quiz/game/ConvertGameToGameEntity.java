package com.winnowsolutions.quiz.game;

import com.winnowsolutions.quiz.question.QuestionEntity;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertGameToGameEntity implements Function<Game, GameEntity> {

    @Override
    public GameEntity apply(Game game) {
        return new GameEntity(
                convertToQuestionEntities(game.getQuestions()),
                game.getGameGuid(),
                game.getQuestionNumber(),
                game.getQuoteNumber(),
                game.getQuestionAnswers());
    }

    private List<QuestionEntity> convertToQuestionEntities(List<Game.Question> questions) {
        return questions
                .stream()
                .map((question ->
                        new QuestionEntity(
                                question.getCelebrityName(),
                                question.getQuotes(),
                                question.getIncorrectAnswers())))
                .collect(Collectors.toList());
    }
}
