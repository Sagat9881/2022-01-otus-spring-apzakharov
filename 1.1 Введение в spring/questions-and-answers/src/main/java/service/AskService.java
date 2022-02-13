package service;

import lombok.Builder;
import lombok.Getter;
import model.Question;
import model.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Getter
public class AskService {

    private final UserInputHandler inputHandler;
    private final Long minScoreSuccess;
    private final CSVService csvReader;
    private List<Question> questionList;

//    public AskService(UserInputHandler inputHandler, Long minScoreSuccess, CSVService csvReader) {
//        this.inputHandler = inputHandler;
//        this.minScoreSuccess = minScoreSuccess;
//        this.csvReader = csvReader;
//    }

    public List<Question> getActiveQuestion() {
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
