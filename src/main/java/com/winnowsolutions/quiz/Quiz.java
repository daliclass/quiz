package com.winnowsolutions.quiz;

import com.winnowsolutions.quiz.game.start.CreateNewGame;
import com.winnowsolutions.quiz.game.summary.CalculateSummary;
import com.winnowsolutions.quiz.game.turn.AnswerQuestion;
import com.winnowsolutions.quiz.game.turn.Turn;
import com.winnowsolutions.quiz.question.QuestionService;
import com.winnowsolutions.quiz.repository.GameRepository;

import java.util.UUID;

/* Facade over the quiz library to hide away the implementation details
 */
public class Quiz {
    private final static Integer NUMBER_OF_QUESTIONS = 10;
    private final CreateNewGame createNewGame;
    private final AnswerQuestion answerQuestion;
    private final CalculateSummary calculateSummary;

    public Quiz(QuestionService questionService, GameRepository gameRepository) {
        this.createNewGame = new CreateNewGame(questionService, gameRepository);
        this.answerQuestion = new AnswerQuestion(gameRepository);
        this.calculateSummary = new CalculateSummary(gameRepository);
    }

    public Turn newGame() {
        return createNewGame.startGame(NUMBER_OF_QUESTIONS);
    }

    public Turn answerQuestion(UUID gameUUID, String answer) {
        return answerQuestion.answer(gameUUID, answer);
    }

    public CalculateSummary.GameSummary calculateSummary(UUID gameUUID) {
        return calculateSummary.forgameUUID(gameUUID);
    }
}
