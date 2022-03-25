package ru.otus.awesomlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.awesomlibrary.domain.Book;
import ru.otus.awesomlibrary.repository.BookDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDao dao;

    public void createBook(Book book) {
        try {
            dao.createBook(book);
        } catch (DataAccessException e) {
            System.out.println("Не получилось создать книгу");
            System.out.println( e.getMessage());
        }
    }

    public Book getBookById(Long id) {
        Book book = null;
        try {
            book = dao.getBookById(id);
        } catch (DataAccessException e) {
            System.out.println("Не получилось создать книгу");

        }
        return book;
    }

    public List<Book> getAllBook(){
        List<Book> bookList = null;
        try {
            bookList = dao.getAllBook();
        }catch (DataAccessException e){
            System.out.println("Не удалось получить все книги");
            System.out.println(e.getMessage());
        }
        return bookList;
    }

    public void deleteById(Long id){
        try {
            dao.deleteBookById(id);
        }catch (DataAccessException e){
            System.out.println("Не удалось удалить книгу");
        }
    }

    public Book getByTitle(String title) {
        Book foundedBook = null;
        try {
            foundedBook= dao.getByTitle(title);
        }catch (DataAccessException e){
            System.out.println("Не удалось найти книгу");
            System.out.println(e.getMessage());
        }

        return foundedBook;
    }

    public void deleteByTitle(String title) {
        try {
            dao.deleteBookByTitle(title);
        }catch (DataAccessException e){
            System.out.println("Не удалось удалить книгу!");
            System.out.println(e.getMessage());
        }
    }
}
