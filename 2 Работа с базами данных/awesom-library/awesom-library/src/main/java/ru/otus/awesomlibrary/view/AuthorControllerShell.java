package ru.otus.awesomlibrary.view;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.awesomlibrary.domain.Author;
import ru.otus.awesomlibrary.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorControllerShell {

    private final AuthorService service;
    private final static String SEPARATOR = "======================";

    @ShellMethod(key = {"create-author", "ca"}, value = "создать автора")
    public void createAuthor(String fullName) {
        service.createAuthor(Author.builder().fullName(fullName).build());
    }

    ;

    @ShellMethod(key = {"delete-author", "da"}, value = "удалить автора")
    public void deleteAuthorByName(String fullName) {
        service.deleteAuthorByName(fullName);
    }

    ;

    @ShellMethod(key = {"get-author-by-name", "gabn"}, value = "получить автора по имени")
    public void getAuthorByName(String fullName) {
        System.out.println(service.getAuthorByName(fullName));
    }

    ;

    @ShellMethod(key = {"get-all-authors", "gaa"}, value = "получить всех авторов")
    public void getAllAuthors() {
        service.getAllAuthor().forEach(author->{
            System.out.println(SEPARATOR+"\n"+author);
        });

    }

    ;


}
