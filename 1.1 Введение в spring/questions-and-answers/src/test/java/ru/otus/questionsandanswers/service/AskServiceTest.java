package ru.otus.questionsandanswers.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.questionsandanswers.model.Question;
import ru.otus.questionsandanswers.model.User;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static ru.otus.questionsandanswers.service.TestUtils.*;


@DisplayName("Сервис оперирования вопросами")
@ExtendWith(MockitoExtension.class)
class AskServiceTest {

    private final UserInputHandler inputHandler = mock(UserInputHandler.class);

    private final CSVService csvReader = mock(CSVService.class);

    @BeforeEach
    void setUp() throws Exception {
        List<Question> list = getListQuestionTest();
        Question question = getTestQuestion();

        given(csvReader.readCSVToQuestion()).willReturn(list);
        given(inputHandler.handleInput()).willReturn("USER_INPUT");


    }

    @DisplayName("Получение списка вопросов")
    @Test
    void getActiveQuestion() throws Exception{
        AskService askService = getAskServiceForTest(csvReader, inputHandler);

        List<Question> list = askService.getActiveQuestion();

        assertThat(askService.getQuestionList()).isNotNull();
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("Отображение строки ввода пользователя")
    void handleInput() {
        AskService askService = getAskServiceForTest(csvReader, inputHandler);

        String userTestInput = askService.handleInput();

        assertThat(userTestInput).isNotNull();
    }


    @Test
    @DisplayName("Подсчет количества баллов по весу вопроса")
    void calculateScore() throws Exception {
        AskService askService = getAskServiceForTest(csvReader, inputHandler);
        List<Question> rigthAnsweredQuestionList = new ArrayList<>();

        askService.getActiveQuestion().forEach(question -> {
            question.setIsAnsweredRight(true);
            rigthAnsweredQuestionList.add(question);
        });


        Long awaitingResult = rigthAnsweredQuestionList.stream()
                                                                .mapToLong(Question::getWeight)
                                                                .sum();

        assertThat(askService.calculateScore()).isEqualTo(awaitingResult);
    }

    @Test
    @DisplayName("Создание нового пользователя")
    void createNewUser() {
        AskService askService = getAskServiceForTest(csvReader, inputHandler);
        String testUserName = "Ivan";

        User testUser = askService.createNewUser(testUserName);

        assertThat(testUser.getName()).isEqualTo(testUserName);
    }
}