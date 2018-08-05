package com.winnowsolutions.quiz.game.start;

import com.winnowsolutions.quiz.repository.Game;
import com.winnowsolutions.quiz.game.entities.GameEntity;
import com.winnowsolutions.quiz.repository.GameRepository;
import com.winnowsolutions.quiz.question.Question;
import com.winnowsolutions.quiz.game.entities.QuestionEntity;
import com.winnowsolutions.quiz.question.QuestionService;
import com.winnowsolutions.quiz.game.turn.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateNewGame {

    private final QuestionService questionService;
    private final GameRepository gameRepository;
    private static final Integer NUMBER_OF_INCORRECT_ANSWERS_PER_QUESTION = 3;

    public CreateNewGame(QuestionService questionService, GameRepository gameRepository) {
        this.questionService = questionService;
        this.gameRepository = gameRepository;
    }

    public Turn startGame(Integer numberOfQuestions) {
        List<QuestionEntity> questions = convertToListQuestionEntity(questionService.getQuestions(numberOfQuestions));
        GameEntity gameEntity = new GameEntity(questions);
        gameRepository.createGame(convertToGame(questions, gameEntity));

        return convertToTurn(gameEntity);
    }

    private List<QuestionEntity> convertToListQuestionEntity(List<Question> questions) {
        List<QuestionEntity> questionEntities = new ArrayList<QuestionEntity>();
        for (Question question : questions) {
            questionEntities.add(convertToQuestionEntity(question));
        }

        return questionEntities;
    }

    private QuestionEntity convertToQuestionEntity(Question question) {
        return new QuestionEntity(
                question.getCelebrityName(),
                new ArrayList<String>(question.getQuotes()),
                questionService.getCelebrityNames(NUMBER_OF_INCORRECT_ANSWERS_PER_QUESTION));
    }

    private Game convertToGame(List<QuestionEntity> questions, GameEntity gameEntity) {
        List<Game.Question> gameQuestions = new ArrayList();

        for (QuestionEntity questionEntity: questions) {
            gameQuestions.add(new Game.Question(
                    questionEntity.getCelebrityName(),
                    questionEntity.getQuotes(),
                    questionEntity.getIncorrectAnswers()));
        }

        Game game = new Game(
                gameQuestions,
                UUID.randomUUID(),
                gameEntity.getQuestionNumber(),
                gameEntity.getQuoteNumber(),
                gameEntity.getQuestionAnswers());

        return game;
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
