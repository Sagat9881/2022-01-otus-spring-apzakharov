package ru.otus.awesomlibrary.repository;

import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.domain.BookGenre;

import java.util.List;

public interface BookGenreDao {

    void createBookGenre(BookGenre genre);
    BookGenre getBookGenreById(Long id);
    List<BookGenre> getAllBookGenre();
    void deleteBookGenreById(Long id);

    BookGenre getForKind(String genreType);
}
