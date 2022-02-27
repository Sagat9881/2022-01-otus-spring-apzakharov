package ru.otus.questionsandanswers.config;

import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.Objects;


@Configuration
public class LocaleConfig {


    private static Locale locale;

    public static Locale getAvalibaleLocale() {
        return Objects.nonNull(locale) ? locale : Locale.getDefault();
    }

    public static void setLocale(Locale newLocale){
        locale = newLocale;
    }
}
