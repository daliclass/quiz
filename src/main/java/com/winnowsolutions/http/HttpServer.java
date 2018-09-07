package com.winnowsolutions.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winnowsolutions.quiz.Quiz;
import com.winnowsolutions.quiz.game.summary.CalculateSummary;
import com.winnowsolutions.quiz.game.turn.Turn;
import io.javalin.Context;
import io.javalin.Handler;
import io.javalin.Javalin;

import java.util.UUID;

public class HttpServer {

    private final Quiz quiz;
    private final Integer port;
    private final AnswerQuestionHandler answerQuestionHandler;
    private final ObjectMapper objectMapper;
    private final NewGameHandler newGameHandler;
    private final GameSummaryHandler gameSummaryHandler;

    public HttpServer(Quiz quiz, Integer port) {
       this.quiz = quiz;
       this.port = port;
       this.newGameHandler = new NewGameHandler();
       this.answerQuestionHandler = new AnswerQuestionHandler();
       this.gameSummaryHandler = new GameSummaryHandler();
       this.objectMapper = new ObjectMapper();
    }

    public void run() {
        Javalin app = Javalin.create().start(port);
        app.get("/game/new", newGameHandler);
        app.post("/question/answer", answerQuestionHandler);
        app.post("/question/answer", gameSummaryHandler);
    }

    private class NewGameHandler implements Handler {

        @Override
        public void handle(Context context) throws Exception {
            context.header("Content-Type", "application/json");
            context.header("charset", "utf-8");
            context.result(objectMapper.writeValueAsString(quiz.newGame()));
        }
    }

    private class AnswerQuestionHandler implements Handler {

        @Override
        public void handle(Context context) throws Exception {
            context.header("Content-Type", "application/json");
            context.header("charset", "utf-8");
            AnswerQuestionBody answerQuestionBody = context.bodyAsClass(AnswerQuestionBody.class);
            Turn turn = quiz.answerQuestion(UUID.fromString(answerQuestionBody.gameUUID), answerQuestionBody.answer);
            context.result(objectMapper.writeValueAsString(turn));
        }

        class AnswerQuestionBody {
            public String gameUUID;
            public String answer;
        }
    }

    private class GameSummaryHandler implements Handler {

        @Override
        public void handle(Context context) throws Exception {
            context.header("Content-Type", "application/json");
            context.header("charset", "utf-8");
            GameSummaryBody gameSummaryBody = context.bodyAsClass(GameSummaryBody.class);
            CalculateSummary.GameSummary turn = quiz.calculateSummary(UUID.fromString(gameSummaryBody.gameUUID));
            context.result(objectMapper.writeValueAsString(turn));
        }

        class GameSummaryBody {
            public String gameUUID;
        }
    }
}
