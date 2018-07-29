package com.winnowsoltutions.quiz.game;

import com.winnowsolutions.quiz.game.GameEntity;
import com.winnowsolutions.quiz.question.QuestionEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTestEntityTest {

    private final List<String> EMPTY_QUOTES = new ArrayList<String>();
    private final List<String> EMPTY_ANSWERS = new ArrayList<String>();
    private final String QUOTE_1 = "Quote 1";
    private final List<String> POPULATED_QUOTES = new ArrayList<String>() {{
        add("Quote 1");
        add("Quote 2");
    }};

    @Test
    public void whenCreatingANewGameThenReceiveTheFirstQuestion() {
        List<QuestionEntity> questionEntityList = new ArrayList(){{
            add(new QuestionEntity("Shia", EMPTY_QUOTES, EMPTY_ANSWERS));
            add(new QuestionEntity("Callum", EMPTY_QUOTES, EMPTY_ANSWERS));
        }};
        GameEntity gameEntity = new GameEntity(questionEntityList);

        assertEquals(questionEntityList.get(0), gameEntity.getCurrentQuestion());
    }

    @Test
    public void whenRequestingAQuoteThenGetTheQuoteForTheCurrentQuestion() {
        List<QuestionEntity> questionEntityList = new ArrayList(){{
            add(new QuestionEntity("Shia", POPULATED_QUOTES, EMPTY_ANSWERS));
            add(new QuestionEntity("Callum", EMPTY_QUOTES, EMPTY_ANSWERS));
        }};
        GameEntity gameEntity = new GameEntity(questionEntityList);

        assertEquals(QUOTE_1, gameEntity.getCurrentQuote());
    }

    @Test
    public void whenCallingNextQuestionThenMoveCurrentQuestionToTheNextQuestion() {
        List<QuestionEntity> questionEntityList = new ArrayList(){{
            add(new QuestionEntity("Shia", POPULATED_QUOTES, EMPTY_ANSWERS));
            add(new QuestionEntity("Callum", EMPTY_QUOTES, EMPTY_ANSWERS));
        }};
        GameEntity gameEntity = new GameEntity(questionEntityList);
        gameEntity.nextQuestion();
        assertEquals(questionEntityList.get(1), gameEntity.getCurrentQuestion());
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
}
