package ru.otus.questionsandanswers.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {

    private final QuestionType type;
    private final String questionBody;
    private final Long weight;
    private final String rightAnswer;


    private Boolean isAnsweredRight;

    public void setAnswer(String answer) {


        switch (type) {
            case CLOSE:
                isAnsweredRight = answer.trim().equalsIgnoreCase(rightAnswer);
                break;
            case OPEN:
                isAnsweredRight = true;
        }

    }
}
