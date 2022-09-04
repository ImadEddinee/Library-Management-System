package com.ensas.librarymanagementsystem.service.impl;

import com.ensas.librarymanagementsystem.entities.Author;
import com.ensas.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.ensas.librarymanagementsystem.repositories.AuthorRepository;
import com.ensas.librarymanagementsystem.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    @Override
    public Page<Author> getAuthors(String keyword, int page, int size) {
        keyword = keyword + "%";
        log.info(keyword);
        return authorRepository.findByName(keyword, PageRequest.of(page, size));
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException("Author with id " + id + " not found"));
    }

    @Override
    public Author addAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        return null;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}


