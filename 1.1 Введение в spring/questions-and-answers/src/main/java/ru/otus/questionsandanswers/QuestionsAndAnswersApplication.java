package ru.otus.questionsandanswers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class QuestionsAndAnswersApplication {


	public static void main(String[] args) throws Exception {
		 SpringApplication.run(QuestionsAndAnswersApplication.class);

	}


}
