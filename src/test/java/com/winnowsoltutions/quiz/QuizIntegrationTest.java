package com.winnowsoltutions.quiz;

import com.winnowsolutions.quiz.Quiz;
import com.winnowsolutions.quiz.game.summary.CalculateSummary;
import com.winnowsolutions.quiz.game.turn.Turn;
import com.winnowsolutions.quiz.question.FileBasedQuestionService;
import com.winnowsolutions.quiz.question.QuestionService;
import com.winnowsolutions.quiz.repository.GameRepository;
import com.winnowsolutions.quiz.repository.InMemoryGameRepository;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QuizIntegrationTest {

    @Test
    public void givenAUserStartsAQuizWhenAUserCompletesTheQuizThenTheyGetACalculatedSummary() {
        QuestionService questionService = new FileBasedQuestionService(new File("./src/test/resources/questions.json"));
        GameRepository gameRepository = new InMemoryGameRepository();
        Quiz quiz = new Quiz(questionService, gameRepository);
        Turn turn = quiz.newGame();
        UUID originalUUID = turn.gameUUID;
        validateTurn(turn, 0, originalUUID);

        Turn nextTurn = quiz.answerQuestion(turn.gameUUID, turn.potentialAnswers.get(0));
        validateTurn(nextTurn, 1, originalUUID);

        nextTurn = quiz.answerQuestion(turn.gameUUID, turn.potentialAnswers.get(0));
        validateTurn(nextTurn, 2, originalUUID);

        nextTurn = quiz.answerQuestion(turn.gameUUID, turn.potentialAnswers.get(0));
        validateTurn(nextTurn, 3, originalUUID);

        nextTurn = quiz.answerQuestion(turn.gameUUID, turn.potentialAnswers.get(0));
        validateTurn(nextTurn, 4, originalUUID);

        nextTurn = quiz.answerQuestion(turn.gameUUID, turn.potentialAnswers.get(0));
        validateTurn(nextTurn, 5, originalUUID);

        assertEquals(nextTurn.questionNumber.intValue(), 5);

        validateGameSummary(quiz.calculateSummary(originalUUID), 6);
    }

    public void validateGameSummary(CalculateSummary.GameSummary gameSummary, Integer maxScore) {
        assertTrue("Score is not populated", gameSummary.getScore() != null);
        assertTrue("Score is not greater than max score", gameSummary.getScore() <= gameSummary.getMaxScore());
        assertEquals(maxScore, gameSummary.getMaxScore());
    }

    public void validateTurn(Turn turn, Integer questionNumber, UUID gameUUID) {
        assertTrue("gameUUID is the same as original gameUUID" ,turn.gameUUID == gameUUID);
        assertEquals(Integer.valueOf(6), turn.numberOfQuestions);
        assertEquals(questionNumber, turn.questionNumber);
        assertTrue("quote is not null or empty string" ,turn.quote != null || turn.quote != "");
        assertTrue("Potential answers is the correct length", turn.potentialAnswers.size() == 4);
        assertEquals(false, turn.potentialAnswers.get(0) == null);
        assertEquals(false, turn.potentialAnswers.get(1) == null);
        assertEquals(false, turn.potentialAnswers.get(2) == null);
        assertEquals(false, turn.potentialAnswers.get(3) == null);
        assertEquals(false, turn.potentialAnswers.get(0).isEmpty());
        assertEquals(false, turn.potentialAnswers.get(1).isEmpty());
        assertEquals(false, turn.potentialAnswers.get(2).isEmpty());
        assertEquals(false, turn.potentialAnswers.get(3).isEmpty());
    }
}
