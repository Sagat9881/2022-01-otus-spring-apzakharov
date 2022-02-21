package ru.otus.awesomlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.awesomlibrary.domain.BookGenre;
import ru.otus.awesomlibrary.exepction.GenreNotFoundException;
import ru.otus.awesomlibrary.repository.BookGenreDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookGenreService {
    private final BookGenreDao dao;

    public void createGenre(BookGenre genre) {
        try {
            dao.createBookGenre(genre);
        } catch (DataAccessException e) {
            System.out.println("Не удалось создать жанр");
        }
    }

    public BookGenre getGenreById(Long id) {
        BookGenre genre = null;
        try {
            genre = dao.getBookGenreById(id);
        } catch (DataAccessException e) {
            System.out.println("Не удалось найти жанр");
        }

        return genre;
    }

    public List<BookGenre> getAllBookGenre() {
        List<BookGenre> bookGenreList = null;
        try {
            bookGenreList = dao.getAllBookGenre();
        } catch (DataAccessException e) {
            System.out.println("Не удалось найти все жанры");
        }
        return bookGenreList;
    }

    public void deleteGenreById(Long id) {
        try {
            dao.deleteBookGenreById(id);
        } catch (DataAccessException e) {
            System.out.println("Не удалось удалить жанр");
        }
    }


    public BookGenre getForGenreType(String genreType) {
        BookGenre genre = null;
        try {
            genre = dao.getForKind(genreType);
        }catch (DataAccessException e){
            System.out.println("Не удалось найти жанр");
            throw new GenreNotFoundException();
        }

        return genre;
    }
}
