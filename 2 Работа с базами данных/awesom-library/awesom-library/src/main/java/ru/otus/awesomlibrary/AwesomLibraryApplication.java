package ru.otus.awesomlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "application.yml")
public class AwesomLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwesomLibraryApplication.class);
	}

}
