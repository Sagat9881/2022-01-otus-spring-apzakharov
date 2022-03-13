package ru.otus.awesomlibrary.repository.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    private static final BookGenre TEST_BOOK_GENRE = BookGenre.builder().genreType(TEST_TYPE).build();
    private static final String TEST_FULLNAME = "Me";
    private static final Author TEST_AUTHOR = Author.builder().fullName(TEST_FULLNAME).build();
    private static final String TEST_TITLE = "TEST_TITLE";

    @Autowired
    private BookDao dao;

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