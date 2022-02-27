package ru.otus.questionsandanswers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;

import ru.otus.questionsandanswers.config.ServiceConfig;
import ru.otus.questionsandanswers.view.ConsoleView;

@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class QuestionsAndAnswersApplication {


	public static void main(String[] args) throws Exception {
	ApplicationContext context = SpringApplication.run(QuestionsAndAnswersApplication.class);

	ConsoleView view = context.getBean(ConsoleView.class);

	view.startTestProcess();

	}


}
