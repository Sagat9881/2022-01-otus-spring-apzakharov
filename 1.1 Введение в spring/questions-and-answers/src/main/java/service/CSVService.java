package service;

import model.Question;
import model.QuestionType;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CSVService {

    private final String resourceURI;

    public CSVService(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public List<Question> readCSVToQuestion() {
        try {
            if (!Objects.nonNull(resourceURI)) {
                throw new FileNotFoundException("Не указан путь к списку вопросов");
            }
            ClassPathResource resource = new ClassPathResource(resourceURI);
            InputStream csvStream = resource.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));

            return reader.lines().map(questLine -> {
                String[] questAsArr = questLine.split(",");

                return Question.builder()
                        .type(QuestionType.valueOf(questAsArr[0]))
                        .questionBody(questAsArr[1])
                        .rightAnswer(questAsArr[2])
                        .weight(Long.valueOf(questAsArr[3]))
                        .isAnsweredRight(false)
                        .build();

            }).collect(Collectors.toList());

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }
}
