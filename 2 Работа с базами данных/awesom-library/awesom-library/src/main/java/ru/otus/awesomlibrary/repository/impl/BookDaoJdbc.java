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
                .update("insert into books (book_id, title, author, book_genre) values (?, ?, ?, ?)",book.getBook_id(), book.getTitle(), book.getAuthor().getAuthor_id(), book.getBookGenre().getBook_genre_id());
    }

    @Override
    public Book getBookById(Long id) {
        return jdbc.getJdbcOperations().queryForObject("select * from books where book_id = ?", new BookMapper(), id);
    }

    @Override
    public List<Book> getAllBook() {
        return jdbc.query("select books.book_id,books.title, " +
                        "book_authors.full_name, book_authors.author_id," +
                        "book_genres.genre_type, book_genres.genre_id " +
                        "from books " +
                        "inner join book_authors on book_authors.author_id = books.author "+
                        "inner join book_genres on book_genres.genre_id = books.book_genre ", new BookMapper());
    }

    @Override
    public void deleteBookById(Long id) {
        jdbc.getJdbcOperations().update("delete from books where book_id = ?", id);
    }

    @Override
    public Book getByTitle(String title) {
        Map<String, Object> paramMap = Map.of("title", title);
        return jdbc.queryForObject("select books.book_id,books.title, " +
                "book_authors.full_name, book_authors.author_id," +
                "book_genres.genre_type, book_genres.genre_id " +
                "from books " +
                "inner join book_authors on book_authors.author_id = books.author "+
                "inner join book_genres on book_genres.genre_id = books.book_genre "+
                "where title = :title ", paramMap, new BookMapper());
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
                    .book_id(rs.getLong("book_id"))
                    .bookGenre(BookGenre.builder()
                            .book_genre_id(rs.getLong("genre_id"))
                            .genreType(rs.getString("genre_type"))
                            .build())
                    .author(Author.builder()
                            .author_id(rs.getLong("author_id"))
                            .fullName(rs.getString("full_name"))
                            .build())
                    .title(rs.getString("title"))
                    .build();
        }
    }
}
