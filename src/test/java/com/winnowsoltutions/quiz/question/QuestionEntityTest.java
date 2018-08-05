package com.winnowsoltutions.quiz.question;

import com.winnowsolutions.quiz.game.entities.QuestionEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class QuestionEntityTest {

    private final List<String> INCORRECT_ANSWERS = new ArrayList<String>() {{
        add("a");add("b");add("c");
    }};

    private final List<String> QUOTES = new ArrayList<String>() {{
        add("d");add("e");add("f");
    }};

    @Test
    public void whenProvidingAnswersForAQuestionThenShuffleThemEachTime() {
        final String celebrityName = "Celene";
        QuestionEntity questionEntity = new QuestionEntity("Celene", QUOTES, INCORRECT_ANSWERS);
        List<String> expectedListOfAnswers = new ArrayList<String>(INCORRECT_ANSWERS) {{add(celebrityName);}};

        assertTrue(expectedListOfAnswers.containsAll(questionEntity.getPotentialAnswers()));
    }
}
