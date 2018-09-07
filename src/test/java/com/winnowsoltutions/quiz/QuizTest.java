package com.winnowsoltutions.quiz;

import com.winnowsolutions.quiz.game.start.CreateNewGame;
import com.winnowsolutions.quiz.Quiz;
import com.winnowsolutions.quiz.game.summary.CalculateSummary;
import com.winnowsolutions.quiz.game.turn.AnswerQuestion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/* This will probably become useless when decent integration tests are in place
 */
public class QuizTest {

    @Mock
    CreateNewGame createNewGame;

    @Mock
    AnswerQuestion answerQuestion;

    @Mock
    CalculateSummary calculateSummary;

    @Before
    public void before() {
        initMocks(this);
    }
}
