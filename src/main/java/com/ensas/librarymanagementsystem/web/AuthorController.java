package com.ensas.librarymanagementsystem.web;

import com.ensas.librarymanagementsystem.entities.Author;
import com.ensas.librarymanagementsystem.service.AuthorService;
import com.ensas.librarymanagementsystem.utils.GeneratePagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthorController {

    private final AuthorService authorService;
    private final GeneratePagination generatePagination;

    @GetMapping("/authors")
    public String getAuthors(Model model,
                             @RequestParam(name = "keyword", defaultValue = "") String keyword,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Author> authorPage = authorService.getAuthors(keyword, page, size);
        model.addAttribute("authors", authorPage.getContent());
        model.addAttribute("pagination", generatePagination.pagination(page));
        model.addAttribute("totalPages", authorPage.getTotalPages());
        model.addAttribute("totalElements", authorPage.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentSize", size);
        model.addAttribute("currentPage", page);
        return "author/list-authors";
    }

    @GetMapping("/authors/{id}")
    public String getAuthor(@PathVariable("id") Long id){
        Author author = authorService.getAuthor(id);
        return "";
    }

    @GetMapping("/add-author")
    public String addBook(Model model){
        model.addAttribute("authors", authorService.getAllAuthors());
        return "author/add-author";
    }

    @PostMapping("/authors")
    public String saveAuthor(@RequestBody Author author) {
        Author addedAuthor = authorService.addAuthor(author);
        return "";
    }

    @DeleteMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
        return "";
    }

    @PutMapping("/authors/{id}")
    public String updateAuthor(@PathVariable("id") Long id,
                               @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return "";
    }
}
