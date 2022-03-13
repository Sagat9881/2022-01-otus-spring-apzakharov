package ru.otus.awesomlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.awesomlibrary.domain.Author;
import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.domain.BookGenre;
import ru.otus.awesomlibrary.exepction.AuthorNotFoundException;
import ru.otus.awesomlibrary.exepction.GenreNotFoundException;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final AuthorService authorService;
    private final BookService bookService;
    private final BookGenreService genreService;

    public void createBook(String title, String authorFullName, String kindGenre, boolean force) {
        Author author = null;
        BookGenre bookGenre = null;
        try {
             author = authorService.getForFullName(authorFullName);

        } catch (AuthorNotFoundException e) {
            if (force) {
                System.out.println("Новый автор будет добавлен");
                author= authorService.createAuthor(Author.builder().fullName(authorFullName).build());
            }
        }

        try {
             bookGenre = genreService.getForGenreType(kindGenre);
        } catch (GenreNotFoundException e) {
            if (force) {
                System.out.println("Новый жанр будет добавлен");
                bookGenre = genreService.createGenre(BookGenre.builder().genreType(kindGenre).build());
            }
        }
        int id = (int) (Math.random() * 10);

        bookService.createBook(Book.builder().book_id((long) id)
                .title(title)
                .author(author)
                .bookGenre(bookGenre)
                .build());

    }

    public Book getBookByTitle(String title) {
        return bookService.getByTitle(title);
    }

    public void deleteByTitle(String title) {
        bookService.deleteByTitle(title);
    }

    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }
}
