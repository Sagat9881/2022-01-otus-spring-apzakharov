package service;

import lombok.RequiredArgsConstructor;
import model.Question;
import model.QuestionType;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AskService {
    private final String resourceURI;
    private List<Question> questionList;

    public List<Question> readCSV() {
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
                        .quest(questAsArr[1])
                        .isAnsweredRight(false)
                        .build();

            }).collect(Collectors.toList());

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    public void ask() {
        if (!Objects.nonNull(questionList)) {
            try {
                questionList = readCSV();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        questionList.stream()
                .filter(question -> !question.getIsAnsweredRight())
                .map(Question::getQuest)
                .forEach(System.out::println);
    }
}
