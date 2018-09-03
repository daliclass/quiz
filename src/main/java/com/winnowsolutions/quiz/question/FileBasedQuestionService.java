package com.winnowsolutions.quiz.question;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileBasedQuestionService implements QuestionService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final List<Question> questions;

    public FileBasedQuestionService(File file) {
        this.questions = parseQuestions(file);
    }

    private static List<Question> parseQuestions(File file) {
        try {
            return objectMapper.readValue(file, JsonWrapper.class).questions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> getQuestions(Integer numberOfQuestions) {
        if (numberOfQuestions > questions.size()) {
            numberOfQuestions = questions.size();
        }
        return questions.subList(0, numberOfQuestions);
    }

    @Override
    public List<String> getCelebrityNames(Integer numberOfCelebrityNames) {
        if (numberOfCelebrityNames > questions.size()) {
            numberOfCelebrityNames = questions.size();
        }
        return questions.stream()
                .map(question -> question.getCelebrityName())
                .collect(Collectors.toList())
                .subList(0, numberOfCelebrityNames);
    }
}
