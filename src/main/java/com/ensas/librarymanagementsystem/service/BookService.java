package com.ensas.librarymanagementsystem.service;

import com.ensas.librarymanagementsystem.entities.Book;
import com.ensas.librarymanagementsystem.entities.Borrow;
import org.springframework.data.domain.Page;

public interface BookService {

    Book saveBook(Book book);

    void deleteBook(Long id);

    Book updateBook(Long id, Book book);

    Page<Book> getBooks(String keyword, int page, int size);

    Book getBook(Long id);

    boolean borrowBook(Long id, String date);

    boolean checkIfAlreadyBorrowed(Long id);

    boolean checkBookQuantity(Long id);

    Page<Borrow> getBorrowedBooks(String keyword, int page, int size);

    void returnBook(Long id);
}
