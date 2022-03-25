package ru.otus.awesomlibrary.view;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.otus.awesomlibrary.domain.BookGenre;
import ru.otus.awesomlibrary.service.BookGenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreControllerShell {

    private final BookGenreService service;
    private final static String SEPARATOR = "======================";
    @ShellMethod(key = {"create-genre", "cg"}, value = "Создание нового жанра")
    public void createGenre(String genreType) {
        service.createGenre(BookGenre
                .builder()
                .genreType(genreType)
                .build());

    }

    @ShellMethod(key = {"delete-genre", "dg"}, value = "удалить жанр")
    public void deleteGenreByType(String genreType) {
        service.deleteGenreByType(genreType);
    }

    @ShellMethod(key = {"get-genre-by-type", "gbty"}, value = "получить жанр по типу")
    public void getGenreByType(String type) {
        System.out.println(
                service.getForGenreType(type));
    }

    @ShellMethod(key = {"get-all-genre", "gag"}, value = "получить все жанры")
    public void getAllGenre() {
        service.getAllBookGenre().forEach(genre->{
            System.out.println(SEPARATOR+"\n"+genre);
        });
    }
}
