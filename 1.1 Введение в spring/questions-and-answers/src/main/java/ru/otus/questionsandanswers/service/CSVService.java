package ru.otus.questionsandanswers.service;



import org.springframework.core.io.ClassPathResource;
import ru.otus.questionsandanswers.model.Question;
import ru.otus.questionsandanswers.model.QuestionType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.otus.questionsandanswers.config.ServiceConfig.getAvalibaleLocale;

public class CSVService {

    private final String resourceURI;

    public CSVService(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public List<Question> readCSVToQuestion() throws FileNotFoundException {

        if (!Objects.nonNull(resourceURI)) {
            throw new FileNotFoundException("Не указан путь к списку вопросов");
        }
        try {
            String resourceURI = this.resourceURI.replace(".csv","_"+getAvalibaleLocale().getLanguage()+".csv");

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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
