package com.projekat.bookStorePJ.Service;

import com.projekat.bookStorePJ.Entity.Book;
import com.projekat.bookStorePJ.Entity.Category;
import com.projekat.bookStorePJ.Model.BookModel;
import com.projekat.bookStorePJ.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final CategoryService categoryService;

    public List<Book> getAllBooks() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Book> getBookById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public List<Book> getBookByAuthor(String author) {
        return repository.findByAuthorContainsAndDeletedAtIsNull(author);
    }

    public Book createBook(BookModel model) {
        Book book = new Book();
        Category ctg = categoryService.getCategoryById(model.getCategoryId()).orElseThrow();
        book.setBook_name(model.getBook_name());
        book.setAuthor(model.getAuthor());
        book.setPrice(Integer.valueOf(model.getPrice()));
        book.setCategory(ctg);
        return repository.save(book);
    }

    public Book updateBook(Integer id, BookModel model) {
        Book book = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        Category ctg = categoryService.getCategoryById(model.getCategoryId()).orElseThrow();
        book.setBook_name(model.getBook_name());
        book.setAuthor(model.getAuthor());
        book.setPrice(Integer.valueOf(model.getPrice()));
        book.setCategory(ctg);
        book.setUpdatedAt(LocalDateTime.now());
        return repository.save(book);
    }

    public void deleteBook(Integer id) {
        Book book = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        book.setDeletedAt(LocalDateTime.now());
        repository.save(book);
    }
}
