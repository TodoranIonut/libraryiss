package com.issproject.bibleoteca.service;

import com.issproject.bibleoteca.model.Book;
import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.repository.BookRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.issproject.bibleoteca.model.Status.AVAILABLE;
import static com.issproject.bibleoteca.model.Status.UNAVAILABLE;

@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final UserService userService;

    @Autowired
    public BookServiceImplementation(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void updateBook(Book book, Integer id) {
        Book findBook = bookRepository.findById(id).orElse(null);
        if (findBook != null && book.getAuthor() != null && book.getTitle() != null && book.getStatus() != null) {
            findBook.setTitle(book.getTitle());
            findBook.setAuthor(book.getAuthor());
            findBook.setStatus(book.getStatus());
            bookRepository.save(findBook);
        }
    }

    @Override
    public void updateBookStatus(String status, Integer id) {
        Book findBook = bookRepository.findById(id).orElse(null);
        if (findBook != null && status != null) {
            if(status.equals(AVAILABLE.name()) || status.equals(UNAVAILABLE.name())) {
                findBook.setStatus(status);
                bookRepository.save(findBook);
            }
        }
    }

    @Override
    public ResponseEntity<User> addBookToUser(int bookId, int userId) {
        Book book = findBookById(bookId);
        if(book.getStatus().equals(AVAILABLE.name())) {
            User user = userService.findUserById(userId);
            book.setUser(user);
            book.setStatus(UNAVAILABLE.name());
            user.addBook(book);
            bookRepository.save(book);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> removeBookFromUser(int bookId) {
        Book book = findBookById(bookId);
        User bookUser = book.getUser();
        book.setUser(null);
        book.setStatus(AVAILABLE.name());
        bookUser.removeBook(book);
        bookRepository.save(book);
        return new ResponseEntity<>("Removed book from user successfully",HttpStatus.OK);
    }
}
