package ru.otus.awesomlibrary.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Author {
    private String fullName;
}
