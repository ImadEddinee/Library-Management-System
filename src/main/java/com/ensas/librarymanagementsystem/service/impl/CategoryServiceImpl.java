package com.ensas.librarymanagementsystem.service.impl;

import com.ensas.librarymanagementsystem.entities.Category;
import com.ensas.librarymanagementsystem.exceptions.CategoryNotFoundException;
import com.ensas.librarymanagementsystem.repositories.CategoryRepository;
import com.ensas.librarymanagementsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> getCategories(String keyword, int page, int size) {
        keyword = keyword + "%";
        return categoryRepository.findByName(keyword, PageRequest.of(page, size));
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category with id " + id + "doesn't exists"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
