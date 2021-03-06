package com.issproject.bibleoteca.controller;

import com.issproject.bibleoteca.model.Book;
import com.issproject.bibleoteca.model.User;
import com.issproject.bibleoteca.service.BookService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('book:write')")
    public ResponseEntity<Book> add(@RequestBody @NotNull Book book) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/book/add").toUriString());
        return ResponseEntity.created(uri).body(bookService.saveBook(book));
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('book:write')")
    public String updateBook(@RequestBody Book book, @PathVariable int id) {
        bookService.updateBook(book, id);
        return "Book " + id + " was updated";
    }

    @PostMapping("/update/{id}/{status}")
    @PreAuthorize("hasAuthority('book:write')")
    public String updateBookStatus(@PathVariable String status, @PathVariable int id) {
        bookService.updateBookStatus(status, id);
        return "Book " + id + " was updated";
    }

    @GetMapping("/getAll")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('book:read')")
    public Book getBookById(@PathVariable @NotNull int id) {
        return bookService.findBookById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('book:write')")
    public String deleteBookById(@PathVariable @NotNull int id) {
        bookService.deleteBook(id);
        return "Book " + id + "was deleted";
    }

    @PostMapping(path ="/addBook/{cbookId}/to/{userId}")
    @PreAuthorize("hasAuthority('book:write')")
    public ResponseEntity<User> addBookUser(@NotNull @PathVariable int bookId,@NotNull @PathVariable int userId){
        return ResponseEntity.ok().body(bookService.addBookToUser(bookId,userId));
    }

    @PostMapping(path ="/remove/{bookId}")
    @PreAuthorize("hasAuthority('book:write')")
    public ResponseEntity<?> removeBookUser(@NotNull @PathVariable int bookId){
        return ResponseEntity.ok().body(bookService.removeBookFromUser(bookId));
    }
}




