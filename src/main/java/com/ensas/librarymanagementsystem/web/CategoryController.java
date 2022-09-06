package com.ensas.librarymanagementsystem.web;

import com.ensas.librarymanagementsystem.entities.Book;
import com.ensas.librarymanagementsystem.entities.Category;
import com.ensas.librarymanagementsystem.service.CategoryService;
import com.ensas.librarymanagementsystem.utils.GeneratePagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final GeneratePagination generatePagination;


    @GetMapping("/categories")
    public String getCategories(Model model,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size){
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

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable("id") Long id,
                             @RequestParam(name = "keyword",defaultValue = "") String keyword,
                             @RequestParam(name = "page",defaultValue = "0") int page,
                             @RequestParam(name = "size",defaultValue = "5") int size){
        categoryService.deleteCategory(id);
        return "redirect:/categories?keyword=" + keyword + "&page=" + page + "&size=" + size;
    }

    @GetMapping("/categories/{id}/books")
    public String getCategoryBooks(@PathVariable("id") Long id,Model model,
                                   @RequestParam(name = "keyword",defaultValue = "") String keyword,
                                   @RequestParam(name = "page",defaultValue = "0") int page,
                                   @RequestParam(name = "size",defaultValue = "5") int size) {
        Page<Book> categoryBooks = categoryService.getCategoryBooks(id, keyword, page, size);
        model.addAttribute("books", categoryBooks.getContent());
        model.addAttribute("pagination", generatePagination.pagination(page));
        model.addAttribute("totalPages", categoryBooks.getTotalPages());
        model.addAttribute("totalElements", categoryBooks.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentSize", size);
        model.addAttribute("currentPage", page);
        return "book/list-books";
    }

    @GetMapping("/update-category/{id}")
    public String getCategoryDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));
        return "category/update-category";
    }

    @PostMapping("/categories")
    public String saveCategory(@Valid Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("category", category);
            return "category/add-category";
        }
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/add-category")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "category/add-category";
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(@PathVariable("id") Long id,@Valid Category category,
                             BindingResult bindingResult,Model model
    ) {
        if (bindingResult.hasErrors()){
            model.addAttribute("category", category);
            return "category/update-category";
        }
        categoryService.updateCategory(id, category);
        return "redirect:/categories";
    }
}
