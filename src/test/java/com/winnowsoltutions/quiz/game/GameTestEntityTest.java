package com.winnowsoltutions.quiz.game;

import com.winnowsolutions.quiz.game.GameEntity;
import com.winnowsolutions.quiz.question.QuestionEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTestEntityTest {

    private final List<String> EMPTY_QUOTES = new ArrayList<String>();
    private final List<String> EMPTY_ANSWERS = new ArrayList<String>();
    private final String QUOTE_1 = "Quote 1";
    private final String CELEB_1 = "Shia";
    private final List<String> POPULATED_QUOTES = new ArrayList<String>() {{
        add("Quote 1");
        add("Quote 2");
    }};
    private final List<QuestionEntity> POPULATED_QUESTIONS = new ArrayList(){{
        add(new QuestionEntity(CELEB_1, POPULATED_QUOTES, EMPTY_ANSWERS));
        add(new QuestionEntity("Callum", POPULATED_QUOTES, EMPTY_ANSWERS));
    }};

    List<QuestionEntity> EMPTY_QUESTIONS = new ArrayList(){{
        add(new QuestionEntity(CELEB_1, EMPTY_QUOTES, EMPTY_ANSWERS));
        add(new QuestionEntity("Callum", EMPTY_QUOTES, EMPTY_ANSWERS));
    }};

    @Test
    public void whenCreatingANewGameThenReceiveTheFirstQuestion() {
        GameEntity gameEntity = new GameEntity(EMPTY_QUESTIONS);

        assertEquals(EMPTY_QUESTIONS.get(0), gameEntity.getCurrentQuestion());
    }

    @Test
    public void whenRequestingAQuoteThenGetTheQuoteForTheCurrentQuestion() {
        GameEntity gameEntity = new GameEntity(POPULATED_QUESTIONS);

        assertEquals(QUOTE_1, gameEntity.getCurrentQuote());
    }

    @Test
    public void whenCallingNextQuestionThenMoveCurrentQuestionToTheNextQuestion() {
        GameEntity gameEntity = new GameEntity(POPULATED_QUESTIONS);
        gameEntity.nextQuestion();
        assertEquals(POPULATED_QUESTIONS.get(1), gameEntity.getCurrentQuestion());
    }

    @Test
    public void whenCallingNextQuestionWhenThereIsNotAnotherQuestionThenReturnNull() {
        List<QuestionEntity> questionEntityList = new ArrayList(){{
            add(new QuestionEntity("Callum", EMPTY_QUOTES, EMPTY_ANSWERS));
        }};
        GameEntity gameEntity = new GameEntity(questionEntityList);
        gameEntity.nextQuestion();
        assertEquals(null, gameEntity.getCurrentQuestion());
    }

    @Test
    public void whenCorrectAnswerProvidedThenReturnTrue() {
        GameEntity gameEntity = new GameEntity(POPULATED_QUESTIONS);
        gameEntity.answerQuestion(CELEB_1);
        assertTrue(gameEntity.currentQuestionCorrect());
    }

    @Test
    public void whenIncorrectAnswerProvidedThenReturnfalse() {
        GameEntity gameEntity = new GameEntity(POPULATED_QUESTIONS);
        gameEntity.answerQuestion("Kevin Hart");
        assertFalse(gameEntity.currentQuestionCorrect());
    }
}
