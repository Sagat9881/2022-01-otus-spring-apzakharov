package service;

import model.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static service.TestUtils.getCsvServiceForTest;

@DisplayName("Сервис чтения CSV файлов")
@ExtendWith(MockitoExtension.class)
class CSVServiceTest {


    @Test
    @DisplayName("Чтение файла с вопросами")
    void readCSVToQuestion() throws FileNotFoundException {
        String nullValidResourceURI = null;
        String exeptionMessage = "expected FileNotFondExepction";
        CSVService csvService = getCsvServiceForTest(nullValidResourceURI);

        assertThrows(FileNotFoundException.class,
                csvService::readCSVToQuestion,exeptionMessage);

        String validResourcePath = "/questions.csv";
        csvService =getCsvServiceForTest(validResourcePath);

        List<Question> questionList = csvService.readCSVToQuestion();

        assertThat(questionList).isNotNull();
    }


}