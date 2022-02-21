package ru.otus.awesomlibrary.repository.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
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
@ExtendWith(SpringExtension.class)
class BookDaoJdbcTest {

    public static final String TEST_TYPE = "MyGenre";
    public static final BookGenre TEST_BOOK_GENRE = BookGenre.builder().genreType(TEST_TYPE).build();
    public static final String TEST_FULLNAME = "Me";
    public static final Author TEST_AUTHOR = Author.builder().fullName(TEST_FULLNAME).build();
    public static final String TEST_TITLE = "TEST_TITLE";
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @Test
    void createBook() {
        BookDao dao = new BookDaoJdbc(jdbc);
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
        BookDao dao = new BookDaoJdbc(jdbc);
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
        BookDao dao = new BookDaoJdbc(jdbc);
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
        BookDao dao = new BookDaoJdbc(jdbc);
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