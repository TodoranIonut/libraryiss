package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.Book;
import com.issproject.bibleoteca.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface BookService {

    Book saveBook(Book book);
    List<Book> findAllBooks();
    void deleteBook(Integer id);
    Book findBookById(Integer id);
    void updateBook(Book book, Integer id);
    void updateBookStatus(String status, Integer id);
    User addBookToUser(int bookId, int userId);
    String removeBookFromUser(int bookId);
}