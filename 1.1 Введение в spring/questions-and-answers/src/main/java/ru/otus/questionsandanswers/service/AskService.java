package ru.otus.questionsandanswers.service;

import lombok.Builder;
import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.questionsandanswers.model.Question;
import ru.otus.questionsandanswers.model.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Service
public class AskService {

    private final UserInputHandler inputHandler;
    private final CSVService csvReader;
    private final Long minScoreSuccess;

    private List<Question> questionList;

    public AskService(UserInputHandler inputHandler,@Value("${minSuccessScore}") Long minScoreSuccess, CSVService csvReader) {
        this.inputHandler = inputHandler;
        this.minScoreSuccess = minScoreSuccess;
        this.csvReader = csvReader;
    }

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

    public Long calculateScore() {
        if(!Objects.nonNull(questionList)){
            return 0L;
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
