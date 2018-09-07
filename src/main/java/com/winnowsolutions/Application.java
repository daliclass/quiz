package com.winnowsolutions;

import com.winnowsolutions.http.HttpServer;
import com.winnowsolutions.quiz.Quiz;
import com.winnowsolutions.quiz.question.FileBasedQuestionService;
import com.winnowsolutions.quiz.question.QuestionService;
import com.winnowsolutions.quiz.repository.GameRepository;
import com.winnowsolutions.quiz.repository.InMemoryGameRepository;

import java.io.File;

public class Application {

    public static void main(String[] args) {
        QuestionService questionService = new FileBasedQuestionService(new File("./src/main/resources/questions.json"));
        GameRepository gameRepository = new InMemoryGameRepository();
        HttpServer httpServer = new HttpServer(new Quiz(questionService, gameRepository), 7001);
        httpServer.run();
    }
}
