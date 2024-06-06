package com.projekat.bookStorePJ.Repository;

import com.projekat.bookStorePJ.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByDeletedAtIsNull();

    Optional<Category> findByIdAndDeletedAtIsNull(Integer id);
}
