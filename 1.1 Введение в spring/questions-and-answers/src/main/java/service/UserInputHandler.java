package service;

import model.Question;

import java.util.Scanner;


public class UserInputHandler {

    private final Scanner scanner = new Scanner(System.in);

    public String handleInput() {
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    public void handleAnswer(Question question) {
        question.setAnswer(handleInput());

    }
}
