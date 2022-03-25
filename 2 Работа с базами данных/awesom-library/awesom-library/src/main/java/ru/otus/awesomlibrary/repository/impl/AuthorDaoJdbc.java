package ru.otus.awesomlibrary.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.awesomlibrary.domain.Author;
import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.repository.AuthorDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Author createAuthor(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("full_name",author.getFullName());
        GeneratedKeyHolder key = new GeneratedKeyHolder();

        jdbc.update("insert into book_authors (full_name) values (:full_name)",params,key);

        author.setAuthor_id((Long)key.getKey());

        return author;
    }

    @Override
    public Author getAuthorById(Long id) {
        return jdbc.getJdbcOperations().queryForObject("select * from book_authors where author_id = ?", new AuthorMapper(),id);
    }

    @Override
    public List<Author> getAllAuthor() {
        return  jdbc.getJdbcOperations().query("select * from book_authors", new AuthorMapper());
    }

    @Override
    public void deleteAuthorById(Long id) {
        jdbc.getJdbcOperations().update("delete from book_authors where author_id = ?",id);
    }

    @Override
    public void deleteAuthorByName(String name) {
        jdbc.getJdbcOperations().update("delete from book_authors where full_name = ?",name);
    }

    @Override
    public Author getByFullName(String authorFullName) {
        return jdbc.getJdbcOperations().queryForObject("select * from book_authors where full_name = ?", new AuthorMapper(),authorFullName);
    }

    private static class AuthorMapper implements RowMapper<Author>{
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                            .author_id(rs.getLong("author_id"))
                            .fullName(rs.getString("full_name"))
                            .build();
        }
    }
}
