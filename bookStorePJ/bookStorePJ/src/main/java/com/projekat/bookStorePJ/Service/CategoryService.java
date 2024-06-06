package com.projekat.bookStorePJ.Service;

import com.projekat.bookStorePJ.Entity.Category;
import com.projekat.bookStorePJ.Model.CategoryModel;
import com.projekat.bookStorePJ.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getAllCategory() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Category> getCategoryById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public Category saveCategory(CategoryModel model) {
        Category category = new Category();
        category.setName(model.getName());
        return repository.save(category);
    }

    public Category updateCategory(Integer id, CategoryModel model) {
        Category category = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        category.setName(model.getName());
        category.setUpdatedAt(LocalDateTime.now());
        return repository.save(category);
    }

    public void deleteCategory(Integer id) {
        Category category = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        category.setDeletedAt(LocalDateTime.now());
        repository.save(category);
    }
}
