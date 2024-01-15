package com.imrzoom.desafioanotaai.services;

import com.imrzoom.desafioanotaai.domain.category.Category;
import com.imrzoom.desafioanotaai.domain.category.dto.CategoryDTO;
import com.imrzoom.desafioanotaai.exceptions.CategoryNotFoundException;
import com.imrzoom.desafioanotaai.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll(){
        return this.repository.findAll();
    }

    public Optional<Category> findById(String id) {
        return this.repository.findById(id);
    }

    public Category create(CategoryDTO categoryDTO) {
        Category newCategory = new Category(categoryDTO);
        this.repository.save(newCategory);
        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryDTO) {
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if (!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if (!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());

        this.repository.save(category);

        return category;
    }

    public void delete(String id) {
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);

    }
}
