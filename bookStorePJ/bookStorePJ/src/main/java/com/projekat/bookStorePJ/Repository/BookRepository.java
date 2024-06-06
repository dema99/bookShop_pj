package com.projekat.bookStorePJ.Repository;

import com.projekat.bookStorePJ.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByDeletedAtIsNull();

    Optional<Book> findByIdAndDeletedAtIsNull(Integer id);

    List<Book> findByAuthorContainsAndDeletedAtIsNull(String author);
}
