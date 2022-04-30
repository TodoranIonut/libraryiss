package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.Book;
import com.issproject.bibleoteca.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface BookService {

    public Book saveBook(Book book);
    public List<Book> findAllBooks();
    public void deleteBook(Integer id);
    public Book findBookById(Integer id);
    public void updateBook(Book book, Integer id);
    public void updateBookStatus(String status, Integer id);
    public ResponseEntity<User> addBookToUser(int bookId, int userId);
    public ResponseEntity<String> removeBookFromUser(int bookId);
}