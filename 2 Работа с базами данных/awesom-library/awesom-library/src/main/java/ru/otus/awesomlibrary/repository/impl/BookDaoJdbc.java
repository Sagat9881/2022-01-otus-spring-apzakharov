package ru.otus.awesomlibrary.repository.impl;

import liquibase.pro.packaged.B;
import liquibase.pro.packaged.O;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.awesomlibrary.domain.Author;
import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.domain.BookGenre;
import ru.otus.awesomlibrary.repository.BookDao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public void createBook(Book book) {
        jdbc.getJdbcOperations()
                .update("insert into books (title, author, bookGenre) values (?, ?, ?)", book.getTitle(), book.getAuthor().getFullName(), book.getBookGenre().getGenreType());
    }

    @Override
    public Book getBookById(Long id) {
        return jdbc.getJdbcOperations().queryForObject("select * from books where id = ?", new BookMapper(), id);
    }

    @Override
    public List<Book> getAllBook() {
        return jdbc.query("select * from books", new BookMapper());
    }

    @Override
    public void deleteBookById(Long id) {
        jdbc.getJdbcOperations().update("delete from books where id = ?", id);
    }

    @Override
    public Book getByTitle(String title) {
        Map<String, Object> paramMap = Map.of("title", title);
        return jdbc.queryForObject("select * from books where title = :title ", paramMap, new BookMapper());
    }

    @Override
    public void deleteBookByTitle(String title) {
        Map<String, Object> paramMap = Map.of("title", title);
        jdbc.update("delete from books where title = :title ",paramMap);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .bookGenre(BookGenre.builder().genreType(rs.getString("bookGenre")).build())
                    .author(Author.builder().fullName(rs.getString("author")).build())
                    .title(rs.getString("title"))
                    .build();
        }
    }
}
