package com.projekat.bookStorePJ.Controller;

import com.projekat.bookStorePJ.Entity.Book;
import com.projekat.bookStorePJ.Model.BookModel;
import com.projekat.bookStorePJ.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/book")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final BookService service;

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getBookById(id));
    }

    @GetMapping(path = "/author/{author}")
    public List<Book> getBookByAuthor(@PathVariable String author) {
        return service.getBookByAuthor(author);
    }

    @PostMapping
    public Book createBook(@RequestBody BookModel book) {
        return service.createBook(book);
    }

    @PutMapping(path = "/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody BookModel book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Integer id) {
        service.deleteBook(id);
    }
}
