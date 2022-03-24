package ru.otus.awesomlibrary.repository;

import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.domain.BookGenre;

import java.util.List;

public interface BookGenreDao {

    BookGenre createBookGenre(BookGenre genre);
    BookGenre getBookGenreById(Long id);
    List<BookGenre> getAllBookGenre();
    void deleteBookGenreById(Long id);
    void deleteBookGenreByType(String type);
    BookGenre getForKind(String genreType);
}
