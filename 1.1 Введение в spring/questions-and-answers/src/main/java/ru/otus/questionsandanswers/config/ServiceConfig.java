package ru.otus.questionsandanswers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.questionsandanswers.service.AskService;
import ru.otus.questionsandanswers.service.CSVService;
import ru.otus.questionsandanswers.service.UserInputHandler;
import ru.otus.questionsandanswers.view.ConsoleView;

import java.util.Locale;
import java.util.Objects;


@Configuration
public class ServiceConfig {

    //    @Value("resourceURI")
//    String resourceURI;
    private static Locale locale;;

    @Bean
    public CSVService csvService(@Value("${resourceURI}") String resourceURI) {
        return new CSVService(resourceURI);
    }

    @Bean
    public UserInputHandler userInputHandler() {
        return new UserInputHandler();
    }

    @Bean
    public AskService askService(CSVService csvReader, UserInputHandler inputHandler, @Value("${minSuccessScore}") Long minScoreSuccess) {
        return AskService.builder()
                .csvReader(csvReader)
                .inputHandler(inputHandler)
                .minScoreSuccess(minScoreSuccess)
                .build();
    }

//    @Bean
//    public ConsoleView consoleView(AskService askService, MessageSource messageSource) {
//        ConsoleView consoleView = new ConsoleView(askService, messageSource);
//        consoleView.setLocale(getAvalibaleLocale());
//
//        return consoleView;
//    }

    public static Locale getAvalibaleLocale() {
        return Objects.nonNull(locale) ? locale : Locale.getDefault();
    }

    public static void setLocale(Locale newLocale){
        locale = newLocale;
    }
}
