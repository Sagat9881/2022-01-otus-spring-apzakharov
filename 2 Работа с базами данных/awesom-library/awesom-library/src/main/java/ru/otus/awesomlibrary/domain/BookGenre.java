package ru.otus.awesomlibrary.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class BookGenre {
    private Long book_genre_id;
    private String genreType;
}
