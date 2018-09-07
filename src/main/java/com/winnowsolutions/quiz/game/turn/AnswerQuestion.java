package com.winnowsolutions.quiz.game.turn;

import com.winnowsolutions.quiz.game.common.ConvertGameToGameEntity;
import com.winnowsolutions.quiz.repository.Game;
import com.winnowsolutions.quiz.game.entities.GameEntity;
import com.winnowsolutions.quiz.repository.GameRepository;
import com.winnowsolutions.quiz.game.entities.QuestionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnswerQuestion {

    private final GameRepository gameRepository;
    private final Function<Game, GameEntity> convertToGameEntity;

    public AnswerQuestion(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.convertToGameEntity = new ConvertGameToGameEntity();
    }

    public Turn answer(UUID gameUUID, String answer) {
        Game game = gameRepository.getGame(gameUUID);
        GameEntity gameEntity = convertToGameEntity.apply(game);

        if(gameEntity.isComplete()) {
            return convertToBlankTurn(gameEntity);
        }

        gameEntity.answerQuestion(answer);
        gameEntity.nextQuestion();
        gameRepository.updateGame(convertToGame(convertToQuestionEntities(game.getQuestions()), gameEntity));
        return convertToTurn(gameEntity);
    }

    private Game convertToGame(List<QuestionEntity> questions, GameEntity gameEntity) {
        List<Game.Question> gameQuestions = new ArrayList();

        for (QuestionEntity questionEntity: questions) {
            gameQuestions.add(new Game.Question(
                    questionEntity.getCelebrityName(),
                    questionEntity.getQuotes(),
                    questionEntity.getIncorrectAnswers()));
        }

        return new Game(
                gameQuestions,
                gameEntity.getgameUUID(),
                gameEntity.getQuestionNumber(),
                gameEntity.getQuoteNumber(),
                gameEntity.getQuestionAnswers());
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

    private Turn convertToTurn(GameEntity gameEntity) {
        Turn turn = new Turn();
        turn.gameUUID = gameEntity.getgameUUID();
        turn.questionNumber = gameEntity.getQuestionNumber();
        turn.numberOfQuestions = gameEntity.getNumberOfQuestions();
        turn.quote = gameEntity.getCurrentQuote();
        turn.potentialAnswers = gameEntity.getCurrentQuestion().getPotentialAnswers();

        return turn;
    }

    private Turn convertToBlankTurn(GameEntity gameEntity) {
        Turn turn = new Turn();
        turn.gameUUID = gameEntity.getgameUUID();
        turn.questionNumber = gameEntity.getQuestionNumber();
        turn.numberOfQuestions = gameEntity.getNumberOfQuestions();
        turn.quote = "";
        turn.potentialAnswers = new ArrayList();

        return turn;
    }
}
