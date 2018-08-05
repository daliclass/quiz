package com.winnowsolutions.quiz.game.entities;

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

    public GameEntity(List<QuestionEntity> questionEntities,
                      UUID gameGuid,
                      Integer questionNumber,
                      Integer quoteNumber,
                      Map<Integer, List<String>> questionAnswers) {
        this.questionEntities = questionEntities;
        this.gameGuid = gameGuid;
        this.questionNumber = questionNumber;
        this.quoteNumber = quoteNumber;
        this.questionAnswers = questionAnswers;
    }

    private static Map<Integer, List<String>> initialiseMap(List<QuestionEntity> questionEntities) {
        HashMap<Integer, List<String>> questionNumberToAnswers = new HashMap<>();
        for (int questionNumber = 0; questionNumber < questionEntities.size(); questionNumber++) {
            questionNumberToAnswers.put(questionNumber, new ArrayList());
        }
        return  questionNumberToAnswers;
    }

    public Double getScore() {
        Double score = 0.0;
        for (int answeredQuestion = 0; answeredQuestion < questionAnswers.size(); answeredQuestion++) {
            score += calculateQuestionScore(answeredQuestion);
        }
        return score;
    }

    private Double calculateQuestionScore(Integer answeredQuestion) {
        String celebrityName = questionEntities.get(answeredQuestion).getCelebrityName();
        if (!questionAnswers.get(answeredQuestion).contains(celebrityName)) {
            return 0.0;
        }
        return 1.0 / questionAnswers.get(answeredQuestion).size();
    }

    public Boolean isComplete() {
        return this.questionNumber >= questionEntities.size();
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

    public Map<Integer, List<String>> getQuestionAnswers() {
        return questionAnswers;
    }
}
