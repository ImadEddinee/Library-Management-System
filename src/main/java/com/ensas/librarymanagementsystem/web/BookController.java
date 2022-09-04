package com.ensas.librarymanagementsystem.web;

import com.ensas.librarymanagementsystem.entities.Book;
import com.ensas.librarymanagementsystem.service.AuthorService;
import com.ensas.librarymanagementsystem.service.BookService;
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
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final GeneratePagination generatePagination;


    @GetMapping("/books")
    public String getBooks(Model model,
            @RequestParam(name = "keyword",defaultValue = "") String keyword,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size){
        Page<Book> bookPage = bookService.getBooks(keyword, page, size);
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("pagination", generatePagination.pagination(page));
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("totalElements", bookPage.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentSize", size);
        model.addAttribute("currentPage", page);
        return "book/list-books";
    }

    @GetMapping("/books/{id}")
    public String getBook(@PathVariable("id") Long id,Model model){
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "book/book-info";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable("id") Long id,
                             @RequestParam(name = "keyword",defaultValue = "") String keyword,
                             @RequestParam(name = "page",defaultValue = "0") int page){
        bookService.deleteBook(id);
        return "redirect:/books?keyword=" + keyword + "&page=" + page;
    }

    @GetMapping("/update-book/{id}")
    public String getBookDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("book", bookService.getBook(id));
        return "book/update-book";
    }

    @PostMapping("/books")
    public String saveBook(@Valid Book book, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            return "book/update-book";
        }
        System.out.println(book.getName());
        System.out.println(book.getCategories().size());
        System.out.println(book.getAuthors().size());
        //Book addedBook = bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/add-book")
    public String addBook(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book/add-book";
    }

    @PutMapping("/books/{id}")
    public String updateBook(@PathVariable("id") Long id,
                             @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return "";
    }
}
