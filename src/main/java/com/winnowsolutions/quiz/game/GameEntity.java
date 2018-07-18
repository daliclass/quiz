package com.winnowsolutions.quiz.game;

import com.winnowsolutions.quiz.question.QuestionEntity;
import java.util.UUID;

import java.util.List;

public class GameEntity {
    private final List<QuestionEntity> questionEntities;
    private final UUID gameGuid;
    private Integer questionNumber;
    private Integer quoteNumber;

    public GameEntity(List<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
        this.gameGuid = UUID.randomUUID();
        this.questionNumber = 0;
        this.quoteNumber = 0;
    }

    public GameEntity(List<QuestionEntity> questionEntities,
                      UUID gameGuid,
                      Integer questionNumber,
                      Integer quoteNumber) {
        this.questionEntities = questionEntities;
        this.gameGuid = gameGuid;
        this.questionNumber = questionNumber;
        this.quoteNumber = quoteNumber;
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
        return questionNumber;
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
}
