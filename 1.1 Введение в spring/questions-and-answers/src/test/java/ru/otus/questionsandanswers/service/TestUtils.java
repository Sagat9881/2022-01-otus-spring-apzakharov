package ru.otus.questionsandanswers.service;


import lombok.experimental.UtilityClass;
import ru.otus.questionsandanswers.model.Question;
import ru.otus.questionsandanswers.model.QuestionType;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TestUtils {

    public static List<Question> getListQuestionTest(){
        List<Question> list = new ArrayList<>();

        list.add(getTestQuestion());
        list.add(getTestQuestion());;

        return list;
    }

    public static Question getTestQuestion(){
        return Question.builder()
                .type(QuestionType.valueOf("CLOSE"))
                .questionBody("TEST_QUESTION")
                .rightAnswer("RIGHT_ANSWER_TEST")
                .weight(10L)
                .isAnsweredRight(false)
                .build();
    }

    public static AskService getAskServiceForTest(CSVService csvService, UserInputHandler inputHandler){
        return  new AskService(inputHandler,10l,csvService);
    }

    public static CSVService getCsvServiceForTest(String resourceURI){
        return new CSVService(resourceURI);
    }


}
