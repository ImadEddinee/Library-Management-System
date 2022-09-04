package com.ensas.librarymanagementsystem.service;

import com.ensas.librarymanagementsystem.entities.Author;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuthorService {
    Page<Author> getAuthors(String keyword, int page, int size);

    Author getAuthor(Long id);

    Author addAuthor(Author author);

    void deleteAuthor(Long id);

    Author updateAuthor(Long id, Author author);

    List<Author> getAllAuthors();
}
