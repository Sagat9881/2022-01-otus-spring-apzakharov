package ru.otus.awesomlibrary.repository.impl;

import liquibase.pro.packaged.S;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.awesomlibrary.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@ExtendWith(SpringExtension.class)
class AuthorDaoJdbcTest {

    public static final String TEST_NAME = "TEST_NAME";
    private static final Long TEST_ID = 2L;
    public static Author TEST_AUTHOR ;
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @BeforeAll
    static void init() {
        TEST_AUTHOR = Author.builder().author_id(TEST_ID).fullName(TEST_NAME).build();
    }

    @Test
    void createAuthor() {
        AuthorDaoJdbc dao = new AuthorDaoJdbc(jdbc);
        dao.createAuthor(TEST_AUTHOR);

        Author authorFromDB = dao.getByFullName(TEST_NAME);

        assertThat(authorFromDB).isEqualTo(TEST_AUTHOR);
    }

    @Test
    void getAllAuthor() {
        AuthorDaoJdbc dao = new AuthorDaoJdbc(jdbc);
        dao.createAuthor(TEST_AUTHOR);

        List<Author> authorList = dao.getAllAuthor();

        assertThat(authorList).isInstanceOf(List.class);
        assertThat(TEST_AUTHOR).isIn(authorList);
    }


    @Test
    void getByFullName() {
        AuthorDaoJdbc dao = new AuthorDaoJdbc(jdbc);
        dao.createAuthor(TEST_AUTHOR);

        Author authorFromDB = dao.getByFullName(TEST_NAME);

        assertThat(authorFromDB).isEqualTo(TEST_AUTHOR);
    }
}