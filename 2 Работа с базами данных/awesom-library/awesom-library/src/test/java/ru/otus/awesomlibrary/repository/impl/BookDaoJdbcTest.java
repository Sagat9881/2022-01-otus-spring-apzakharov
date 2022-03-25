package ru.otus.awesomlibrary.repository.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.awesomlibrary.domain.Author;
import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.domain.BookGenre;
import ru.otus.awesomlibrary.repository.BookDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@PropertySource(value = "application.yml")
@Import(BookDaoJdbc.class)
@ExtendWith(SpringExtension.class)
class BookDaoJdbcTest {
    private static final String TEST_TYPE = "MyGenre";
    private static final String TEST_FULL_NAME = "Me";
    private static final String TEST_TITLE = "TEST_TITLE";
    private static final Long TEST_ID = 1L;

    private static BookGenre TEST_BOOK_GENRE;
    private static Author TEST_AUTHOR;


    @Autowired
    private BookDao dao;

    @BeforeAll
    static void init() {
        TEST_BOOK_GENRE = BookGenre.builder().book_genre_id(TEST_ID).genreType(TEST_TYPE).build();
        TEST_AUTHOR = Author.builder().author_id(TEST_ID).fullName(TEST_FULL_NAME).build();
    }

    @Test
    void createBook() {

        Book testCreatedBook = Book.builder()
                .bookGenre(TEST_BOOK_GENRE)
                .author(TEST_AUTHOR)
                .title(TEST_TITLE)
                .build();

        dao.createBook(testCreatedBook);
        Book bookFromDB = dao.getByTitle(TEST_TITLE);

        assertThat(bookFromDB).isEqualTo(testCreatedBook);
    }

    @Test
    void getAllBook() {

        Book testCreatedBook = Book.builder()
                .bookGenre(TEST_BOOK_GENRE)
                .author(TEST_AUTHOR)
                .title(TEST_TITLE)
                .build();

        dao.createBook(testCreatedBook);

        List<Book> bookList = dao.getAllBook();

        assertThat(bookList).isInstanceOf(List.class);
        assertThat(testCreatedBook).isIn(bookList);
    }

    @Test
    void getByTitle() {

        Book testCreatedBook = Book.builder()
                .bookGenre(TEST_BOOK_GENRE)
                .author(TEST_AUTHOR)
                .title(TEST_TITLE)
                .build();

        dao.createBook(testCreatedBook);
        Book bookFromDB = dao.getByTitle(TEST_TITLE);

        assertThat(bookFromDB).isEqualTo(testCreatedBook);
    }

    @Test
    void deleteBookByTitle() {

        Book testCreatedBook = Book.builder()
                .bookGenre(TEST_BOOK_GENRE)
                .author(TEST_AUTHOR)
                .title(TEST_TITLE)
                .build();

        dao.createBook(testCreatedBook);
        dao.deleteBookByTitle(TEST_TITLE);

        assertThatThrownBy(() -> dao.getByTitle(TEST_TITLE)).isInstanceOf(DataAccessException.class);
    }
}