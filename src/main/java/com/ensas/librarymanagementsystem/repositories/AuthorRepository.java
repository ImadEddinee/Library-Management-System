package com.ensas.librarymanagementsystem.repositories;

import com.ensas.librarymanagementsystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
}
