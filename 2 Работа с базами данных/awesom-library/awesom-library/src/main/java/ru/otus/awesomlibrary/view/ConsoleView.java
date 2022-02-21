package ru.otus.awesomlibrary.view;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;
import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.service.LibraryService;

import java.util.List;
import java.util.Objects;

@Component
@ShellComponent
@RequiredArgsConstructor
public class ConsoleView {

    private final LibraryService libraryService;
    private final static String BOOK_SEPARATOR = "======================";

    @ShellMethod(key = "create-book", value = "Add a new book to the library", prefix = "")
    public void createBook(String title, String authorFullName, String genre, @ShellOption(defaultValue = "false") boolean f) {

        libraryService.createBook(title, authorFullName, genre, f);
    }

    @ShellMethod(key = {"get-book-by-title", "single"}, value = "Get book from library by title", prefix = "")
    public void getBookByTitle(String title) {
        Book foundedBook = libraryService.getBookByTitle(title);

        if (Objects.nonNull(foundedBook)) {
            System.out.println(foundedBook.toString());
        }
    }

    @ShellMethod(key = {"get-all-books", "all"}, value = "Get all books in the library")
    private void getAllBookInLibrary() {
        List<Book> bookList = libraryService.getAllBook();

        if (Objects.isNull(bookList) || bookList.isEmpty()) {
            System.out.println("В библиотеке нет книг!");
            return;
        }

        bookList.forEach(book -> {
            System.out.println(BOOK_SEPARATOR + "\n" + book.toString());
        });
    }

    @ShellMethod(key = {"delete-by-title", "d"}, value = "Delete book from library by title", prefix = "")
    public void deleteBookByTitle(String title) {
        libraryService.deleteByTitle(title);
    }
}
