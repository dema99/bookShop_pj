package com.projekat.bookStorePJ.Controller;

import com.projekat.bookStorePJ.Entity.Category;
import com.projekat.bookStorePJ.Model.CategoryModel;
import com.projekat.bookStorePJ.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/category")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAllCategory();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getCategoryById(id));
    }

    @PostMapping
    public Category create(@RequestBody CategoryModel model) {
        return service.saveCategory(model);
    }

    @PutMapping(path = "/{id}")
    public Category update(@PathVariable Integer id, @RequestBody CategoryModel model) {
        return service.updateCategory(id, model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deleteCategory(id);
    }

}
