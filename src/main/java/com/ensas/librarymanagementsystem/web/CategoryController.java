package com.ensas.librarymanagementsystem.web;

import com.ensas.librarymanagementsystem.entities.Category;
import com.ensas.librarymanagementsystem.service.CategoryService;
import com.ensas.librarymanagementsystem.utils.GeneratePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final GeneratePagination generatePagination;

    @GetMapping("/categories")
    public String getCategories(Model model,
                                @RequestParam(name = "keyword", defaultValue = "") String keyword,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Category> categoryPage = categoryService.getCategories(keyword, page, size);
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("pagination", generatePagination.pagination(page));
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("totalElements", categoryPage.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentSize", size);
        model.addAttribute("currentPage", page);
        return "category/list-categories";
    }

    @GetMapping("/categories/{id}")
    public String getCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getCategory(id);
        return "";
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "";
    }

    @PostMapping("/categories")
    public String addCateogrie(@RequestBody Category category){
        Category addedCategory = categoryService.saveCategory(category);
        return "";
    }

    @PutMapping("/categories/{id}")
    public String updateCategorie(@PathVariable("id") Long id,
                                  @RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(id, category);
        return "";
    }
}
