package model;

import lombok.*;

@Setter
@Getter
@Builder
public class Question {

    private final QuestionType type;
    private final String quest;
    private Boolean isAnsweredRight;

}
