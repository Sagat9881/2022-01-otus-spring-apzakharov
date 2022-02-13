package view;

import model.Question;
import model.User;
import service.AskService;

import java.util.Objects;


public class ConsoleView {

    private final AskService service;
    private User user;

    public ConsoleView(AskService service) {
        this.service = service;
    }

    public void startTestProcess() {
        System.out.println("Добро пожаловать в программу тестирования!");

        startTest();
        endTest();
    }

    private void startTest() {

        if (!Objects.nonNull(user)) {
            System.out.println("Пользователь не зарегестрирован в программе тестирования! " +
                    "\nПожалуйста, пройдетие регистрацию");
            registrateUser();

        }
        System.out.println("Минимальный бал для прохождения: " + service.getMinScoreSuccess());
        System.out.println("Удачного тестирования!");
        service.getActiveQuestion().forEach(this::ask);
    }

    private void endTest() {
        user.setScore(service.calculateScore());

        boolean isSuccess = user.getScore() >= service.getMinScoreSuccess();
        String result = (isSuccess) ? "Успех" : "Неудача";

        System.out.println("Ваш результат: " + result);
        System.out.println("Количество набранных баллов: " + user.getScore());

        System.out.println("Повторить тестирование? (Y/N)");
        String answer = service.handleInput();

        if (answer.equalsIgnoreCase("Y")){
            user.setScore(0L);
            startTest();
        } else System.out.println("Спасибо за прохождения теста!");



    }

    private void ask(Question question) {
        System.out.println("Вопрос: " + question.getQuestionBody());
        System.out.println("Баллов за успешный ответ: " + question.getWeight());
        System.out.print("Ответ: ");
        service.handleAnswer(question);
    }

    private void registrateUser() {
        System.out.print("Введите имя: ");
        try {
            String name = service.handleInput();
            user = service.createNewUser(name);
        } catch (Exception e) {
            registrateUser();
        }

        System.out.println("Пользователь " + user.getName() + " успешно зарегестрирован в системе!");

    }
}
