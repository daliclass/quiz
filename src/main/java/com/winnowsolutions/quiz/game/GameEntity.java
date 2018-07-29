package com.winnowsolutions.quiz.game;

import com.winnowsolutions.quiz.question.QuestionEntity;

import java.util.*;

public class GameEntity {
    private final List<QuestionEntity> questionEntities;
    private final UUID gameGuid;
    private Integer questionNumber;
    private Integer quoteNumber;
    private Map<Integer, List<String>> questionAnswers;

    public GameEntity(List<QuestionEntity> questionEntities) {
        this(questionEntities, UUID.randomUUID(), 0 ,0);
    }

    public GameEntity(List<QuestionEntity> questionEntities,
                      UUID gameGuid,
                      Integer questionNumber,
                      Integer quoteNumber) {
        this.questionEntities = questionEntities;
        this.gameGuid = gameGuid;
        this.questionNumber = questionNumber;
        this.quoteNumber = quoteNumber;
        this.questionAnswers = initialiseMap(questionEntities);
    }

    private static Map<Integer, List<String>> initialiseMap(List<QuestionEntity> questionEntities) {
        HashMap<Integer, List<String>> questionNumberToAnswers = new HashMap<>();
        for (int questionNumber = 0; questionNumber < questionEntities.size(); questionNumber++) {
            questionNumberToAnswers.put(questionNumber, new ArrayList());
        }
        return  questionNumberToAnswers;
    }

    public QuestionEntity getCurrentQuestion() {
        if (questionNumber >= questionEntities.size())
            return null;
        return questionEntities.get(questionNumber);
    }

    public UUID getGameGuid() {
        return gameGuid;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public Integer getQuoteNumber() {
        return quoteNumber;
    }

    public Integer getNumberOfQuestions() {
        return questionEntities.size();
    }

    public String getCurrentQuote() {
        return questionEntities.get(questionNumber).getQuotes().get(quoteNumber);
    }

    public void nextQuestion() {
        this.questionNumber = questionNumber + 1;
    }

    public void answerQuestion(String celebrityName) {
        questionAnswers.get(this.questionNumber).add(celebrityName);
    }

    public boolean currentQuestionCorrect() {
        return questionAnswers.get(this.questionNumber).contains(this.getCurrentQuestion().getCelebrityName());
    }

    public Map<Integer, List<String>> getQuestionAnswers() {
        return questionAnswers;
    }
}
