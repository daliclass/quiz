package com.winnowsoltutions.questionwriters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winnowsolutions.quiz.question.Question;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.winnowsolutions.quiz.question.JsonWrapper;


public class QuestionWriter {

    public void writeQuestionsToFile() throws IOException {
        List<Question> questionList = new ArrayList();
        questionList.add(new Question("Oscar Wilde", new ArrayList() {{
            add("Be yourself; everyone else is already taken.");
            add("Always forgive your enemies; nothing annoys them so much.");
            add("To live is the rarest thing in the world. Most people exist, that is all.");
        }}));
        questionList.add(new Question("Albert Einstein", new ArrayList() {{
            add("Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.");
            add("There are only two ways to live your life. One is as though nothing is a miracle. The other is as though everything is a miracle.");
            add("I am enough of an artist to draw freely upon my imagination. Imagination is more important than knowledge. Knowledge is limited. Imagination encircles the world.");
        }}));

        questionList.add(new Question("Mahatma Gandhi", new ArrayList() {{
            add("Be the change that you wish to see in the world.");
            add("Live as if you were to die tomorrow. Learn as if you were to live forever.");
            add("An eye for an eye will only make the whole world blind.");
        }}));

        questionList.add(new Question("J.K. Rowling", new ArrayList() {{
            add("If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.");
            add("It is our choices, Harry, that show what we truly are, far more than our abilities.");
            add("It does not do to dwell on dreams and forget to live.");
        }}));

        questionList.add(new Question("Jane Austen", new ArrayList() {{
            add("The person, be it gentleman or lady, who has not pleasure in a good novel, must be intolerably stupid.");
            add("There is nothing I would not do for those who are really my friends. I have no notion of loving people by halves, it is not my nature.");
            add("I declare after all there is no enjoyment like reading! How much sooner one tires of any thing than of a book! -- When I have a house of my own, I shall be miserable if I have not an excellent library.");
        }}));

        questionList.add(new Question("Leonardo da Vinci", new ArrayList() {{
            add("Tears come from the heart and not from the brain.");
            add("Learning never exhausts the mind.");
            add("Where there is shouting, there is no true knowledge.");
        }}));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonWrapper jsonWrapper = new JsonWrapper();
        jsonWrapper.questions = questionList;

        objectMapper.writeValue(new File("./src/main/resources/questions.json"), jsonWrapper);
    }
}
