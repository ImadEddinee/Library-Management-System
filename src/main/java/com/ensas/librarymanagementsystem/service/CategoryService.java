package com.ensas.librarymanagementsystem.service;

import com.ensas.librarymanagementsystem.entities.Book;
import com.ensas.librarymanagementsystem.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<Category> getCategories(String keyword, int page, int size);

    Category getCategory(Long id);

    void deleteCategory(Long id);

    Category saveCategory(Category category);

    Category updateCategory(Long id, Category category);

    List<Category> getAllCategories();

    Page<Book> getCategoryBooks(Long id, String keyword, int page, int size);
}
