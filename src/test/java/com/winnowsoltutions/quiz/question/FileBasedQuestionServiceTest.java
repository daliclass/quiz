package com.winnowsoltutions.quiz.question;

import com.winnowsolutions.quiz.question.FileBasedQuestionService;
import com.winnowsolutions.quiz.question.Question;
import com.winnowsolutions.quiz.question.QuestionService;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileBasedQuestionServiceTest {

    List<Question> expectedQuestions = new ArrayList() {{
            add(new Question("Oscar Wilde", new ArrayList() {{
                add("Be yourself; everyone else is already taken.");
                add("Always forgive your enemies; nothing annoys them so much.");
                add("To live is the rarest thing in the world. Most people exist, that is all.");
            }}));

            add(new Question("Albert Einstein", new ArrayList() {{
                add("Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.");
                add("There are only two ways to live your life. One is as though nothing is a miracle. The other is as though everything is a miracle.");
                add("I am enough of an artist to draw freely upon my imagination. Imagination is more important than knowledge. Knowledge is limited. Imagination encircles the world.");
            }}));

            add(new Question("Mahatma Gandhi", new ArrayList() {{
                add("Be the change that you wish to see in the world.");
                add("Live as if you were to die tomorrow. Learn as if you were to live forever.");
                add("An eye for an eye will only make the whole world blind.");
            }}));

            add(new Question("J.K. Rowling", new ArrayList() {{
                add("If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.");
                add("It is our choices, Harry, that show what we truly are, far more than our abilities.");
                add("It does not do to dwell on dreams and forget to live.");
            }}));

            add(new Question("Jane Austen", new ArrayList() {{
                add("The person, be it gentleman or lady, who has not pleasure in a good novel, must be intolerably stupid.");
                add("There is nothing I would not do for those who are really my friends. I have no notion of loving people by halves, it is not my nature.");
                add("I declare after all there is no enjoyment like reading! How much sooner one tires of any thing than of a book! -- When I have a house of my own, I shall be miserable if I have not an excellent library.");
            }}));

            add(new Question("Leonardo da Vinci", new ArrayList() {{
                add("Tears come from the heart and not from the brain.");
                add("Learning never exhausts the mind.");
                add("Where there is shouting, there is no true knowledge.");
            }}));
        }};

    @Test
    public void whenRequestingLessQuestionsThanExistInFileStorageThenProvideTheCorrectAmountOfQuestions() {
        QuestionService questionService = new FileBasedQuestionService(new File("./src/test/resources/questions.json"));
        List<Question> questions = questionService.getQuestions(6);
        assertTrue(expectedQuestions.containsAll(questions));
        assertEquals(6, questions.size());
    }

    @Test
    public void whenRequestingMoreQuestionsThanExistInFileStorageThenProvideAsManyQuestionsAsCan() {
        QuestionService questionService = new FileBasedQuestionService(new File("./src/test/resources/questions.json"));
        List<Question> questions = questionService.getQuestions(7);
        assertTrue(expectedQuestions.containsAll(questions));
        assertEquals(6, questions.size());
    }

    @Test
    public void whenRequestingLessQuestionsThanExistInFileStorageThenProvideCorrectNumberOfQuestions() {
        QuestionService questionService = new FileBasedQuestionService(new File("./src/test/resources/questions.json"));
        List<Question> questions = questionService.getQuestions(4);

        assertTrue(expectedQuestions.containsAll(questions));
        assertEquals(4, questions.size());
    }

    @Test
    public void whenRequestingCelebrityNamesFromTheQuestionServiceThenGetCorrectNumberOfCelebrityNames() {
        QuestionService questionService = new FileBasedQuestionService(new File("./src/test/resources/questions.json"));
        List<String> celebrityNames = questionService.getCelebrityNames(6);
        List<String> expectedCelebrityNames = expectedQuestions.stream()
                .map(question -> question.getCelebrityName())
                .collect(Collectors.toList());

        assertTrue(expectedCelebrityNames.containsAll(celebrityNames));
        assertEquals(6, expectedCelebrityNames.size());
    }

    @Test
    public void whenRequestingToManyCelebrityNamesFromTheQuestionServiceThenGetThenGetAsManyCelebrityNamesAsCan() {
        QuestionService questionService = new FileBasedQuestionService(new File("./src/test/resources/questions.json"));
        List<String> celebrityNames = questionService.getCelebrityNames(7);

        List<String> expectedCelebrityNames = expectedQuestions.stream()
                .map(question -> question.getCelebrityName())
                .collect(Collectors.toList());

        assertTrue(expectedCelebrityNames.containsAll(celebrityNames));
        assertEquals(6, expectedCelebrityNames.size());
    }
}
