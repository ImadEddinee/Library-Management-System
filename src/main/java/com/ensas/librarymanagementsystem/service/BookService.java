package com.ensas.librarymanagementsystem.service;

import com.ensas.librarymanagementsystem.entities.Book;
import org.springframework.data.domain.Page;

public interface BookService {

    Book saveBook(Book book);

    void deleteBook(Long id);

    Book updateBook(Long id, Book book);

    Page<Book> getBooks(String keyword, int page, int size);

    Book getBook(Long id);
}
