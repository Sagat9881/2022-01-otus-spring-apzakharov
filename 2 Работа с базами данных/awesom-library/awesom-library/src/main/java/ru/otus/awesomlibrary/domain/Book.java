package ru.otus.awesomlibrary.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Book {
    private Long book_id;
    private String title;
    private Author author;
    private BookGenre bookGenre;
}
