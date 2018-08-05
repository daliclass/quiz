package com.winnowsoltutions.quiz.game.common;

import com.winnowsolutions.quiz.repository.Game;

import java.util.*;

public class GameTestHelper {

    public static Game createCompletedGame() {
        return createCompletedGame(UUID.randomUUID());
    }

    public static Game createCompletedGame(UUID gameGuid) {
        String adam = "Adam";
        String jill = "Jill";
        UUID gameUuid = gameGuid;
        Integer questionNumber = 10;
        Integer quoteNumber = 0;
        List<Game.Question> questions = new ArrayList(){{
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(jill, null, null));
            add(new Game.Question(jill, null, null));
            add(new Game.Question(jill, null, null));
            add(new Game.Question(jill, null, null));
            add(new Game.Question(jill, null, null));
        }};
        Map<Integer, List<String>> questionAnswers = new HashMap() {{
            put(0, new ArrayList(){{ add(jill); add(adam); }});
            put(1, new ArrayList(){{ add(adam); }});
            put(2, new ArrayList(){{ add(adam); }});
            put(3, new ArrayList(){{ add(adam); }});
            put(4, new ArrayList(){{ add(adam); }});
            put(5, new ArrayList(){{ add(adam); }});
            put(6, new ArrayList(){{ add(adam); }});
            put(7, new ArrayList(){{ add(adam); }});
            put(8, new ArrayList(){{ add(adam); }});
            put(9, new ArrayList(){{ add(adam); }});
        }};

        return new Game(questions, gameUuid, questionNumber, quoteNumber, questionAnswers);
    }

    public static Game createIncompleteGame() {
        return createIncompleteGame(UUID.randomUUID());
    }

    public static Game createIncompleteGame(UUID gameGuid) {
        String adam = "Adam";
        String jill = "Jill";
        UUID gameUuid = gameGuid;
        Integer questionNumber = 1;
        Integer quoteNumber = 0;
        List<Game.Question> questions = new ArrayList(){{
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
            add(new Game.Question(adam, null, null));
        }};
        Map<Integer, List<String>> questionAnswers = new HashMap() {{
            put(0, new ArrayList(){{ add(jill); add(adam); }});
        }};

        return new Game(questions, gameUuid, questionNumber, quoteNumber, questionAnswers);
    }
}
