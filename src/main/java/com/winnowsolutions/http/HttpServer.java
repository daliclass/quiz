package com.winnowsolutions.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winnowsolutions.http.requestbodys.AnswerQuestionBody;
import com.winnowsolutions.http.requestbodys.GameSummaryBody;
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
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AnswerQuestionHandler answerQuestionHandler = new AnswerQuestionHandler();
    private final NewGameHandler newGameHandler = new NewGameHandler();
    private final GameSummaryHandler gameSummaryHandler = new GameSummaryHandler();
    private final AllowPostGetOptionsDelete allowPostGetOptionsDelete = new AllowPostGetOptionsDelete();

    public HttpServer(Quiz quiz, Integer port) {
       this.quiz = quiz;
       this.port = port;
    }

    public void run() {
        Javalin app = Javalin.create().start(port);
        app.get("/game/new", newGameHandler);
        app.options("/question/answer", allowPostGetOptionsDelete);
        app.post("/question/answer", answerQuestionHandler);
        app.options("/game/summary", allowPostGetOptionsDelete);
        app.post("/game/summary", gameSummaryHandler);
    }

    private class AllowPostGetOptionsDelete implements Handler {

        @Override
        public void handle(Context context) throws Exception {
            context.header("Access-Control-Allow-Origin", "*");
            context.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            context.header("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
            context.result("");
        }
    }

    private class NewGameHandler implements Handler {

        @Override
        public void handle(Context context) throws Exception {
            context.header("Content-Type", "application/json");
            context.header("charset", "utf-8");
            context.header("Access-Control-Allow-Origin", "*");
            context.result(objectMapper.writeValueAsString(quiz.newGame()));
        }
    }

    private class AnswerQuestionHandler implements Handler {

        @Override
        public void handle(Context context) throws Exception {
            context.header("Content-Type", "application/json");
            context.header("charset", "utf-8");
            context.header("Access-Control-Allow-Origin", "*");
            context.header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            AnswerQuestionBody answerQuestionBody = context.bodyAsClass(AnswerQuestionBody.class);
            Turn turn = quiz.answerQuestion(UUID.fromString(answerQuestionBody.gameUUID), answerQuestionBody.answer);
            context.result(objectMapper.writeValueAsString(turn));
        }
    }

    private class GameSummaryHandler implements Handler {

        @Override
        public void handle(Context context) throws Exception {
            context.header("Content-Type", "application/json");
            context.header("charset", "utf-8");
            context.header("Access-Control-Allow-Origin", "*");
            GameSummaryBody gameSummaryBody = context.bodyAsClass(GameSummaryBody.class);
            CalculateSummary.GameSummary turn = quiz.calculateSummary(UUID.fromString(gameSummaryBody.gameUUID));
            context.result(objectMapper.writeValueAsString(turn));
        }
    }
}
