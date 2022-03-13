package ru.otus.awesomlibrary.repository;

import ru.otus.awesomlibrary.domain.Author;
import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.domain.BookGenre;

import java.util.List;

public interface AuthorDao {

    Author createAuthor(Author author);
    Author getAuthorById(Long id);
    List<Author> getAllAuthor();
    void deleteAuthorById(Long id);

    Author getByFullName(String authorFullName);
}
