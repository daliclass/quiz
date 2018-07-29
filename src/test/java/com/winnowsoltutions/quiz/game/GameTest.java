package com.winnowsoltutions.quiz.game;

import com.winnowsolutions.quiz.game.Game;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void whenComparingEqualityOfTwoGameEntityThatAreEquivilentThenReturnTrue() {
        UUID gameUuid = UUID.randomUUID();
        Game gameOne = createGame(gameUuid);
        Game gameTwo = createGame(gameUuid);

        assertEquals(gameOne, gameTwo);
    }

    private Game createGame(UUID gameUuid) {
        List<String> quotes = new ArrayList() {{
            add("Quote: 1");
        }};

        List<String> incorrectAnswers = new ArrayList() {{
            add("Answer: 1");
        }};

        List<Game.Question> questions = new ArrayList() {{
            add(new Game.Question("Jusin Beiber", quotes, incorrectAnswers));
        }};

        Integer questionNumber = 1;
        Integer quoteNumber = 1;
        return new Game(questions, gameUuid, questionNumber, quoteNumber, new HashMap() {{put(1, new ArrayList(){{add("ANSWERS");}});}});
    }
}
