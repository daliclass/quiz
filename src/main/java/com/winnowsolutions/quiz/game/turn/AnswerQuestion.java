package com.winnowsolutions.quiz.game.turn;

import com.winnowsolutions.quiz.game.Game;
import com.winnowsolutions.quiz.game.GameEntity;
import com.winnowsolutions.quiz.game.GameRepository;
import com.winnowsolutions.quiz.question.QuestionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerQuestion {

    private final GameRepository gameRepository;

    public AnswerQuestion(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Turn answer(String gameGuid, String answer) {
        Game game = gameRepository.getGame(gameGuid);

        List<QuestionEntity> questionEntities = game.getQuestions()
                .stream()
                .map((question ->
                        new QuestionEntity(
                                question.getCelebrityName(),
                                question.getQuotes(),
                                question.getIncorrectAnswers())))
                .collect(Collectors.toList());

        GameEntity gameEntity = new GameEntity(
                questionEntities,
                game.getGameGuid(),
                game.getQuestionNumber(),
                game.getQuoteNumber());

        gameEntity.nextQuestion();
        return convertToTurn(gameEntity);
    }

    private Turn convertToTurn(GameEntity gameEntity) {
        Turn turn = new Turn();
        turn.gameGuid = gameEntity.getGameGuid().toString();
        turn.questionNumber = gameEntity.getQuestionNumber();
        turn.numberOfQuestions = gameEntity.getNumberOfQuestions();
        turn.quote = gameEntity.getCurrentQuote();
        turn.potentialAnswers = gameEntity.getCurrentQuestion().getPotentialAnswers();

        return turn;
    }
}
