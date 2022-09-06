package com.ensas.librarymanagementsystem.web;

import com.ensas.librarymanagementsystem.entities.Author;
import com.ensas.librarymanagementsystem.entities.Book;
import com.ensas.librarymanagementsystem.service.AuthorService;
import com.ensas.librarymanagementsystem.utils.GeneratePagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthorController {

    private final AuthorService authorService;
    private final GeneratePagination generatePagination;


    @GetMapping("/authors")
    public String getAuthors(Model model,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size){
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
    public String getAuthor(@PathVariable("id") Long id,Model model){
        Author author = authorService.getAuthor(id);
        model.addAttribute("author", author);
        return "author/author-info";
    }

    @DeleteMapping("/authors/{id}")
    public String deleteAuthor(@PathVariable("id") Long id,
                             @RequestParam(name = "keyword",defaultValue = "") String keyword,
                             @RequestParam(name = "page",defaultValue = "0") int page,
                             @RequestParam(name = "size",defaultValue = "5") int size){
        authorService.deleteAuthor(id);
        return "redirect:/authors?keyword=" + keyword + "&page=" + page + "&size=" + size;
    }

    @GetMapping("/update-author/{id}")
    public String getAuthorDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("author", authorService.getAuthor(id));
        return "author/update-author";
    }

    @PostMapping("/authors")
    public String saveAuthor(@Valid Author author, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("author", author);
            return "author/add-author";
        }
        authorService.addAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/add-author")
    public String addAuthor(Model model){
        model.addAttribute("author", new Author());
        return "author/add-author";
    }

    @GetMapping("/authors/{id}/books")
    public String getAuthorBooks(@PathVariable("id") Long id,Model model,
                                   @RequestParam(name = "keyword",defaultValue = "") String keyword,
                                   @RequestParam(name = "page",defaultValue = "0") int page,
                                   @RequestParam(name = "size",defaultValue = "5") int size) {
        Page<Book> categoryBooks = authorService.getAuthorBooks(id, keyword, page, size);
        model.addAttribute("books", categoryBooks.getContent());
        model.addAttribute("pagination", generatePagination.pagination(page));
        model.addAttribute("totalPages", categoryBooks.getTotalPages());
        model.addAttribute("totalElements", categoryBooks.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentSize", size);
        model.addAttribute("currentPage", page);
        return "book/list-books";
    }

    @PutMapping("/authors/{id}")
    public String updateAuthor(@PathVariable("id") Long id,@Valid Author author,
                             BindingResult bindingResult,Model model
    ) {
        if (bindingResult.hasErrors()){
            model.addAttribute("author", author);
            return "author/update-author";
        }
        authorService.updateAuthor(id, author);
        return "redirect:/authors";
    }
}
