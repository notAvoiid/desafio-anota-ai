package com.imrzoom.desafioanotaai.controllers;

import com.imrzoom.desafioanotaai.domain.category.Category;
import com.imrzoom.desafioanotaai.domain.category.dto.CategoryDTO;
import com.imrzoom.desafioanotaai.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO categoryDTO){
        Category newCategory = this.service.create(categoryDTO);
        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> newCategory = this.service.findAll();
        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> findById(@PathVariable String id){
        Optional<Category> newCategory = this.service.findById(id);
        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = this.service.update(id, categoryDTO);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
