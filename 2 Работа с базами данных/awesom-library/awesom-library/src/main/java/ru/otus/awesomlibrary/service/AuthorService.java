package ru.otus.awesomlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.awesomlibrary.domain.Author;
import ru.otus.awesomlibrary.exepction.AuthorNotFoundException;
import ru.otus.awesomlibrary.repository.AuthorDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorDao dao;

    public Author createAuthor(Author author) {
        try {
          return  dao.createAuthor(author);
        } catch (DataAccessException ex) {
            System.out.println("Не удалось создать автора");
        }
        return null;
    }

    public Author getAuthorById(Long id) {
        Author author = null;
        try {
            author = dao.getAuthorById(id);
        } catch (DataAccessException ex) {
            System.out.println("Не удалось найти автора");
        }
        return author;
    }

    public List<Author> getAllAuthor() {
        List<Author> authorList = null;
        try {
            authorList = dao.getAllAuthor();
        } catch (DataAccessException ex) {
            System.out.println("Не удалось найти всех авторов");
        }
        return authorList;
    }

    public void deleteAuthorById(Long id) {
        try {
            dao.deleteAuthorById(id);
        } catch (DataAccessException ex) {
            System.out.println("Не удалось удалить автора");
        }
    }

    public Author getForFullName(String authorFullName) {
        Author author = null;
        try {
            author = dao.getByFullName(authorFullName);
        } catch (DataAccessException e) {
            System.out.println("Не удалось найти автора");
            throw new AuthorNotFoundException();
        }
        return author;
    }
}
