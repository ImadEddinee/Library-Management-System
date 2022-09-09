package com.ensas.librarymanagementsystem.web;

import com.ensas.librarymanagementsystem.entities.Book;
import com.ensas.librarymanagementsystem.entities.Borrow;
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
                             @RequestParam(name = "page",defaultValue = "0") int page,
                             @RequestParam(name = "size",defaultValue = "5") int size){
        bookService.deleteBook(id);
        return "redirect:/books?keyword=" + keyword + "&page=" + page + "&size=" + size;
    }

    @GetMapping("/update-book/{id}")
    public String getBookDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("book", bookService.getBook(id));
        return "book/update-book";
    }

    @GetMapping("/borrowed-books")
    public String getBorrowedBooks(Model model,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size){
        Page<Borrow> borrowedBooks = bookService.getBorrowedBooks(keyword, page, size);
        model.addAttribute("borrows", borrowedBooks.getContent());
        model.addAttribute("pagination", generatePagination.pagination(page));
        model.addAttribute("totalPages", borrowedBooks.getTotalPages());
        model.addAttribute("totalElements", borrowedBooks.getTotalElements());
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentSize", size);
        model.addAttribute("currentPage", page);
        return "book/list-borrowed";
    }

    @PostMapping("/books")
    public String saveBook(@Valid Book book, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book/add-book";
        }
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/add-book")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book/add-book";
    }

    @GetMapping("/return-book/{id}")
    public String returnBook(@PathVariable("id") Long id,Model model){
        bookService.returnBook(id);
        return "redirect:/borrowed-books";
    }


    @GetMapping("/borrow/{id}")
    public String borrow(@PathVariable("id") Long id, Model model,
                         @RequestParam(name = "date", defaultValue = "") String date) {
        String error;
        if (!bookService.checkIfAlreadyBorrowed(id)) {
            if (bookService.checkBookQuantity(id)) {
                if (bookService.borrowBook(id, date)) {
                    return "redirect:/books";
                } else {
                    error = "Max Period is three month from now !";
                }
            } else {
                error = "there is no book left";
            }
        } else {
            error = "you have already borrowed this book";
        }
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("error", error);
        return "book/borrow-book";
    }


    @GetMapping("/borrow-book/{id}")
    public String borrowBook(@PathVariable("id") Long id, Model model){
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        model.addAttribute("error", "");
        return "book/borrow-book";
    }

    @PutMapping("/books/{id}")
    public String updateBook(@PathVariable("id") Long id,@Valid Book book,
                             BindingResult bindingResult,Model model
                             ) {
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("book", book);
            return "book/update-book";
        }
        bookService.updateBook(id, book);
        return "redirect:/books";
    }
}
