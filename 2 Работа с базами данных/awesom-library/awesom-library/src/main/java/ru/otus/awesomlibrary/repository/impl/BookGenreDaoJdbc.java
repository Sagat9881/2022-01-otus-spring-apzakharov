package ru.otus.awesomlibrary.repository.impl;

import io.micrometer.core.instrument.MultiGauge;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.domain.BookGenre;
import ru.otus.awesomlibrary.repository.BookGenreDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookGenreDaoJdbc implements BookGenreDao {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public BookGenre createBookGenre(BookGenre genre) {
        int id = (int) (Math.random() * 10);
        genre.setBook_genre_id((long) id);
        jdbc.getJdbcOperations().update("insert into book_genres (genre_id, genre_type) values (?,?)", genre.getBook_genre_id(), genre.getGenreType());
        return genre;
    }

    @Override
    public BookGenre getBookGenreById(Long id) {
        return jdbc.getJdbcOperations().queryForObject("select * from book_genres where genre_id=?", new BookGenreMapper(), id);
    }

    @Override
    public List<BookGenre> getAllBookGenre() {
        return jdbc.getJdbcOperations().query("select * from book_genres", new BookGenreMapper());
    }

    @Override
    public void deleteBookGenreById(Long id) {
        jdbc.getJdbcOperations().update("delete from book_genres where genre_id=?", id);
    }

    @Override
    public BookGenre getForKind(String genreType) {
        return jdbc.getJdbcOperations().queryForObject("select * from book_genres where genre_type = ?", new BookGenreMapper(), genreType);
    }

    private static class BookGenreMapper implements RowMapper<BookGenre> {

        @Override
        public BookGenre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return BookGenre.builder()
                    .book_genre_id(rs.getLong("genre_id"))
                    .genreType(rs.getString("genre_type"))
                    .build();
        }
    }
}
