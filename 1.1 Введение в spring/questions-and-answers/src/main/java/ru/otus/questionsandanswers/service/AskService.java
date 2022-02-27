package ru.otus.questionsandanswers.service;

import lombok.Builder;
import lombok.Getter;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.questionsandanswers.model.Question;
import ru.otus.questionsandanswers.model.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Getter
@Service
public class AskService {

    private final UserInputHandler inputHandler;
    private final CSVService csvReader;
    private List<Question> questionList;;

    @Value("${minSuccessScore}")
    private final Long minScoreSuccess;

    public List<Question> getActiveQuestion() throws Exception {
        if (!Objects.nonNull(questionList)) {
            questionList = csvReader.readCSVToQuestion();
        }
        return questionList.stream()
                .filter(question -> !question.getIsAnsweredRight())
                .collect(Collectors.toList());
    }

    public String handleInput() {
        return inputHandler.handleInput();
    }

    public void handleAnswer(Question question) {
        inputHandler.handleAnswer(question);
    }

    @SneakyThrows
    public Long calculateScore() {
        if(!Objects.nonNull(questionList)){
            questionList = csvReader.readCSVToQuestion();
        }

        return questionList.stream()
                .filter(Question::getIsAnsweredRight)
                .mapToLong(Question::getWeight)
                .sum();
    }

    public User createNewUser(String name) {
        return User.builder()
                .name(name)
                .score(0L)
                .build();
    }


}
