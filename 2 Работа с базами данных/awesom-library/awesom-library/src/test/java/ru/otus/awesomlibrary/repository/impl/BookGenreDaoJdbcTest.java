package ru.otus.awesomlibrary.repository.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.awesomlibrary.domain.BookGenre;
import ru.otus.awesomlibrary.repository.BookGenreDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
@ExtendWith(SpringExtension.class)
class BookGenreDaoJdbcTest {

    public static final String TEST_TYPE = "TEST_TYPE";
    public static final BookGenre TEST_BOOK_GENRE = BookGenre.builder().genreType(TEST_TYPE).build();

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @Test
    void createBookGenre() {
        BookGenreDao testDAO = new BookGenreDaoJdbc(jdbc);
        testDAO.createBookGenre(TEST_BOOK_GENRE);
        BookGenre createdBookGenre = testDAO.getForKind(TEST_TYPE);

        assertThat(createdBookGenre).isEqualTo(TEST_BOOK_GENRE);
    }

    @Test
    void getAllBookGenre() {
        BookGenreDao testDAO = new BookGenreDaoJdbc(jdbc);
        testDAO.createBookGenre(TEST_BOOK_GENRE);

        List<BookGenre> allBookGenre = testDAO.getAllBookGenre();
        List<BookGenre> expectedList = List.of(TEST_BOOK_GENRE);

        assertThat(allBookGenre).isInstanceOf(List.class);
        assertThat(allBookGenre).isNotEmpty();
    }

    @Test
    void getForKind() {
        BookGenreDao testDAO = new BookGenreDaoJdbc(jdbc);
        testDAO.createBookGenre(TEST_BOOK_GENRE);

        BookGenre createdBookGenre = testDAO.getForKind(TEST_TYPE);

        assertThat(createdBookGenre).isEqualTo(TEST_BOOK_GENRE);
    }
}