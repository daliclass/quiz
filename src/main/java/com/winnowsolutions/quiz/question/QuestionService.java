package com.winnowsolutions.quiz.question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions(Integer numberOfQuestions);
    List<String> getCelebrityNames(int i);
}
