package com.issproject.bibleoteca.controller;

import com.issproject.bibleoteca.model.Book;
import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.service.BookService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public String add(@RequestBody @NotNull Book book) {
        bookService.saveBook(book);
        return "New book was added";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@RequestBody Book book, @PathVariable int id) {
        bookService.updateBook(book, id);
        return "Book " + id + " was updated";
    }

    @PostMapping("/update/{id}/{status}")
    public String updateBookStatus(@PathVariable String status, @PathVariable int id) {
        bookService.updateBookStatus(status, id);
        return "Book " + id + " was updated";
    }

    @GetMapping("/getAll")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable @NotNull int id) {
        return bookService.findBookById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBookById(@PathVariable @NotNull int id) {
        bookService.deleteBook(id);
        return "Book " + id + "was deleted";
    }

    @PostMapping(path ="/addBook/{bookId}/to/{userId}")
    public ResponseEntity<User> addBookUser(@NotNull @PathVariable int bookId,@NotNull @PathVariable int userId){
        return bookService.addBookToUser(bookId,userId);
    }

    @PostMapping(path ="/remove/{bookId}")
    public ResponseEntity<String> removeBookUser(@NotNull @PathVariable int bookId){
        return bookService.removeBookFromUser(bookId);
    }
}




