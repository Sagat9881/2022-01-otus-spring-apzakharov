package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import service.AskService;
import service.CSVService;
import service.UserInputHandler;
import view.ConsoleView;

@PropertySource("application.properties")
@Configuration
public class ServiceConfig {

//    @Value("resourceURI")
//    String resourceURI;

    @Bean
    public CSVService csvService(@Value("${resourceURI}") String resourceURI){
        return new CSVService(resourceURI);
    }

    @Bean
    public UserInputHandler userInputHandler(){
        return new UserInputHandler();
    }

    @Bean
    public AskService askService(CSVService csvReader,UserInputHandler inputHandler,@Value("${minSuccessScore}") Long minScoreSuccess){
        return AskService.builder()
                                    .csvReader(csvReader)
                                    .inputHandler(inputHandler)
                                    .minScoreSuccess(minScoreSuccess)
                                    .build();
    }

    @Bean
    public ConsoleView consoleView(AskService askService){
        return new ConsoleView(askService);
    }
}
