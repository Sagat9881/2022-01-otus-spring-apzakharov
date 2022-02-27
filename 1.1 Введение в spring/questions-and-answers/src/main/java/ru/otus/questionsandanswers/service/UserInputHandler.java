package ru.otus.questionsandanswers.service;


import org.springframework.stereotype.Service;
import ru.otus.questionsandanswers.model.Question;

import java.util.Scanner;

@Service
public class UserInputHandler {

    private final Scanner scanner = new Scanner(System.in);;

    public String handleInput() {
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    public void handleAnswer(Question question) {
        question.setAnswer(handleInput());

    }
}
