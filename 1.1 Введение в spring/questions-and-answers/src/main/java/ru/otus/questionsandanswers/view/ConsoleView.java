package ru.otus.questionsandanswers.view;


import org.springframework.context.MessageSource;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.questionsandanswers.model.Question;
import ru.otus.questionsandanswers.model.User;
import ru.otus.questionsandanswers.service.AskService;


import java.util.Locale;
import java.util.Objects;

import static ru.otus.questionsandanswers.config.ServiceConfig.getAvalibaleLocale;

@ShellComponent
public class ConsoleView {

    private final AskService service;;
    private final MessageSource messageSource;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    private Locale locale = getAvalibaleLocale();
    private User user;

    public ConsoleView(AskService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }
    @ShellMethod(key = "start-test",value="Start test")
    public void startTestProcess() throws Exception {

        System.out.println( messageSource.getMessage("console.string.start",null,locale));

        startTest();
        endTest();
    }

    private void startTest() throws Exception {

        if (!Objects.nonNull(user)) {
            messageSource.getMessage("console.string.need-registration",null,locale);
            System.out.println(messageSource.getMessage("console.string.need-registration",null,locale));
            registrateUser();

        }
        System.out.println(messageSource.getMessage("console.string.min-score-for-success",null,locale)
                + service.getMinScoreSuccess());

        System.out.println(messageSource.getMessage("console.string.good-luck",null,locale));

        service.getActiveQuestion().forEach(this::ask);
    }

    private void endTest() throws Exception{
        user.setScore(service.calculateScore());

        boolean isSuccess = user.getScore() >= service.getMinScoreSuccess();
        String codeResult = (isSuccess) ? "console.string.success" : "console.string.fail";

        System.out.println(messageSource.getMessage("console.string.result",null,locale)
                + messageSource.getMessage(codeResult,null,locale));

        System.out.println(messageSource.getMessage("console.string.score",null,locale) + user.getScore());

        System.out.println(messageSource.getMessage("console.string.restart-testing",null,locale));
        String answer = service.handleInput();

        if (answer.equalsIgnoreCase("Y")){
            user.setScore(0L);
            startTest();

        } else System.out.println(messageSource.getMessage("console.string.thanks-for-testing",null,locale));



    }

    private void ask(Question question) {

        System.out.println(messageSource.getMessage("console.string.question",null,locale) + question.getQuestionBody());
        System.out.println(messageSource.getMessage("console.string.question-weight",null,locale) + question.getWeight());
        System.out.print(messageSource.getMessage("console.string.answer",null,locale));
        service.handleAnswer(question);
    }

    private void registrateUser() {

        System.out.print(messageSource.getMessage("console.string.enter-name",null,locale));
        try {
            String name = service.handleInput();
            user = service.createNewUser(name);
        } catch (Exception e) {
            registrateUser();
        }

        System.out.println(messageSource.getMessage("console.string.user",null,locale) + user.getName()
                + messageSource.getMessage("console.string.successe-registration",null,locale));

    }


}
