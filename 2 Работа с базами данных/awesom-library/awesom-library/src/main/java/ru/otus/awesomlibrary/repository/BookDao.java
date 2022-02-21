package ru.otus.awesomlibrary.repository;

import ru.otus.awesomlibrary.domain.Book;

import java.util.List;

public interface BookDao {

    void createBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBook();
    void deleteBookById(Long id);

    Book getByTitle(String title);

    void deleteBookByTitle(String title);
}
